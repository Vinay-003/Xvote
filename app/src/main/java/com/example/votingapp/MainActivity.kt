package com.example.votingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.votingapp.ui.theme.Theme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                var currentPage: BasePage by remember { mutableStateOf(HomePage()) }
                val sampleElections = ElectionData.sampleElections

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (currentPage) {
                        is HomePage -> (currentPage as HomePage).Content { newPage -> currentPage = newPage }
                        is LoginPage -> (currentPage as LoginPage).Content(sampleElections) { newPage -> currentPage = newPage }
                        is SignupPage -> (currentPage as SignupPage).Content(sampleElections) { newPage -> currentPage = newPage }
                        is ProfilePage -> (currentPage as ProfilePage).Content()
                        is SelectElectionPage -> (currentPage as SelectElectionPage).Content { newPage -> currentPage = newPage }
                        is SelectCandidatePage -> (currentPage as SelectCandidatePage).Content { newPage -> currentPage = newPage }
                        is IdDetailsPage -> (currentPage as IdDetailsPage).Content { newPage -> currentPage = newPage }
                        is CastVotePage -> (currentPage as CastVotePage).Content { newPage -> currentPage = newPage }
                        is VoteConfirmationPage -> (currentPage as VoteConfirmationPage).Content()
                    }
                }
            }
        }
    }
}