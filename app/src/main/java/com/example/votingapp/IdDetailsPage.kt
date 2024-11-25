package com.example.votingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class IdDetailsPage(private val candidate: Candidate) : BasePage() {
    override val title: String = "ID Details"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        var voterIdNumber by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page Title
            Text(
                text = "Enter Your Voter ID",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4285F4)
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Candidate Image
            Image(
                painter = painterResource(id = candidate.imageResId),
                contentDescription = "Candidate Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
            )

            // Candidate Name Display
            Text(
                text = "Candidate: ${candidate.name}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Candidate Address Display
            Text(
                text = "Address: ${candidate.candidateAddress}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Candidate Party Display
            Text(
                text = "Party: ${candidate.party}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Candidate Description Display
            Text(
                text = candidate.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Voter ID Input
            OutlinedTextField(
                value = voterIdNumber,
                onValueChange = { voterIdNumber = it },
                label = { Text("Voter ID Number") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                singleLine = true
            )

            // Error Message
            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Next Button
            Button(
                onClick = {
                    when {
                        voterIdNumber.isEmpty() -> errorMessage = "Voter ID Number cannot be empty"
                        else -> {
                            setCurrentPage(CastVotePage(candidate))
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,

                    )

                )
            }
        }
    }
}