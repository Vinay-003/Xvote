package com.example.votingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegisterBasePage(private val election: Election) : BasePage() {
    override val title: String = "Register for ${election.name}"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6)) // Light theme background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image at the top
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_2), // Replace with your image resource
                    contentDescription = "Registration Image",
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.White, shape = CircleShape)
                )

                Spacer(modifier = Modifier.height(50.dp))

                // Title
                Text(
                    text = "Register for ${election.name} ",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xFF4285F4),
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Buttons
                Button(
                    onClick = {
                        // Define the action for the first button
                        setCurrentPage(SignupPage())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))
                ) {
                    Text(
                        text = "Register as a Candidate",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Define the action for the second button
                        setCurrentPage(SignupPage())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))
                ) {
                    Text(
                        text = "Continue as a Voter",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}
