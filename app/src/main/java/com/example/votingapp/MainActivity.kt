package com.example.votingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.votingapp.ui.theme.Theme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private val pageStack = mutableStateListOf<BasePage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                var currentPage by remember { mutableStateOf<BasePage>(HomePage()) }
                val sampleElections = ElectionData.sampleElections
                val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

                LaunchedEffect(currentPage) {
                    if (pageStack.isEmpty() || pageStack.last() != currentPage) {
                        pageStack.add(currentPage)
                    }
                }

                BackHandler(onBackPressedDispatcher) {
                    if (pageStack.size > 1) {
                        pageStack.removeLast()
                        currentPage = pageStack.last()
                    } else {
                        finish()
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (currentPage) {
                        is HomePage -> (currentPage as HomePage).Content { newPage: BasePage -> currentPage = newPage }
                        is Register -> (currentPage as Register).Content { newPage: BasePage -> currentPage = newPage }
                        is RegisterBasePage -> (currentPage as RegisterBasePage).Content { newPage: BasePage -> currentPage = newPage }
                        is NotificationsPage -> (currentPage as NotificationsPage).Content { newPage: BasePage -> currentPage = newPage }
                        is TermsAndConditions -> (currentPage as TermsAndConditions).Content { newPage: BasePage -> currentPage = newPage }
                        is LoginPage -> (currentPage as LoginPage).Content(sampleElections) { newPage: BasePage -> currentPage = newPage }
                        is SignupPage -> (currentPage as SignupPage).Content(sampleElections) { newPage: BasePage -> currentPage = newPage }
                        is ProfilePage -> (currentPage as ProfilePage).Content { newPage: BasePage -> currentPage = newPage }
                        is SelectElectionPage -> (currentPage as SelectElectionPage).Content { newPage: BasePage -> currentPage = newPage }
                        is SelectCandidatePage -> (currentPage as SelectCandidatePage).Content { newPage: BasePage -> currentPage = newPage }
                        is IdDetailsPage -> (currentPage as IdDetailsPage).Content { newPage: BasePage -> currentPage = newPage }
                        is CastVotePage -> (currentPage as CastVotePage).Content { newPage: BasePage -> currentPage = newPage }
                        is VoteConfirmationPage -> (currentPage as VoteConfirmationPage).Content { newPage: BasePage -> currentPage = newPage }
                    }
                }
            }
        }
    }
}

@Composable
fun BackHandler(
    onBackPressedDispatcher: OnBackPressedDispatcher?,
    onBack: () -> Unit
) {
    val currentOnBack by rememberUpdatedState(onBack)
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBack()
            }
        }
    }

    DisposableEffect(onBackPressedDispatcher) {
        onBackPressedDispatcher?.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}