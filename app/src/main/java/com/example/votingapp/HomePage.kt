package com.example.votingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class HomePage : BasePage() {
    override val title: String = "Home"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        val sampleElections = listOf(
            Election(
                "Election 1",
                listOf(Candidate("Candidate 1", "gcg"), Candidate("Candidate 2", "hv"))
            ),
            Election(
                "Election 2",
                listOf(Candidate("Candidate 3", " ngv"), Candidate("Candidate 4", "bhb"))
            )
        )
        val isUser = false // Placeholder for user authentication state
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Login and Signup buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { setCurrentPage(LoginPage()) }) {
                        Text("Login", color = Color.White)
                    }
                    Button(onClick = { setCurrentPage(SignupPage()) }) {
                        Text("Signup", color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
                // Carousel placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 16.dp)
                        .border(1.dp, Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Carousel", color = Color.White)
                }
                Spacer(modifier = Modifier.height(90.dp))
                // List of voting options
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(4) { index ->
                        Button(
                            onClick = {
                                if (isUser) {
                                    // Navigate to the voting page
                                    setCurrentPage(SelectElectionPage(sampleElections))
                                } else {
                                    // Navigate to the login page
                                    setCurrentPage(LoginPage())
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF800080))
                        ) {
                            Text(
                                "Vote for election ${index + 1}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(5f)) // Space to push bottom navigation to the bottom

                // Bottom navigation
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 1.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { setCurrentPage(HomePage()) }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "home", tint = Color.White)
                    }
                    IconButton(onClick = { setCurrentPage(HomePage()) }) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "News section", tint = Color.White)
                    }
                    IconButton(onClick = { setCurrentPage(LoginPage()) }) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "Profile page", tint = Color.White)
                    }
                }
            }
        }
    }
}