package com.example.votingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.votingapp.CandidateData.sampleCandidates

class CastVotePage(private val candidate: Candidate) : BasePage() {
    override val title: String = "Cast Vote"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Candidate Image or Illustration
            Image(
                painter = painterResource(id = candidate.imageResId), // Replace with a valid candidate image resource ID
                contentDescription = "Candidate Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 24.dp)
            )

            // Voting Prompt
            Text(
                text = "You are about to vote for:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Gray,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = candidate.name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4285F4)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Confirmation Prompt
            Text(
                text = "Please confirm your vote.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Cast Vote Button
            Button(
                onClick = { setCurrentPage(VoteConfirmationPage(candidate)) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Green for confirmation
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Cast Vote",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cancel Button
            Button(
                onClick = { setCurrentPage(SelectCandidatePage(sampleCandidates)) }, // Navigate back to home
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5252)), // Red for cancel
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Cancel",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
