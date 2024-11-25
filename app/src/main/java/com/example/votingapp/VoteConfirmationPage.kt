package com.example.votingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class VoteConfirmationPage(private val candidate: Candidate) : BasePage() {
    override val title: String = "Vote Confirmation"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Success Icon or Illustration
            Image(
                painter = painterResource(id = R.drawable.img), // Replace with your success icon resource
                contentDescription = "Success",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 24.dp)
            )

            // Confirmation Message
            Text(
                text = "Vote Cast Successfully!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50) // Green color to indicate success
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Candidate Name
            Text(
                text = "You have voted for:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = candidate.name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4285F4) // Green color to indicate success
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Back to Home Button
            Button(
                onClick = { setCurrentPage(HomePage()) }, // Navigate back to home
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)),
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Back to Home",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
