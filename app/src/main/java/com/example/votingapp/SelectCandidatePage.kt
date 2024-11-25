package com.example.votingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SelectCandidatePage(private val candidates: List<Candidate>) : BasePage() {
    override val title: String = "Select Candidate"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6)) // Light theme background
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Enhanced Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF4285F4), Color(0xFF3FAD82))
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Select Your Candidate",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 24.sp
                        ),
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                // Candidate List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(candidates) { candidate ->
                        CandidateCard(candidate = candidate) {
                            setCurrentPage(IdDetailsPage(candidate))
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun CandidateCard(candidate: Candidate, onClick: () -> Unit) {
        Card(
            elevation = CardDefaults.cardElevation(6.dp),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Candidate Image
                Image(
                    painter = painterResource(id = candidate.imageResId),
                    contentDescription = "Candidate Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Candidate Details
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = candidate.name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xFF4285F4),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "Party: ${candidate.party}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    )

                    Text(
                        text = "Address: ${candidate.candidateAddress}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))
                    ) {
                        Text(
                            text = "View Details",
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}
