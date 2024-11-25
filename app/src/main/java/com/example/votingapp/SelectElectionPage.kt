package com.example.votingapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.votingapp.ElectionData.sampleElections
import kotlinx.coroutines.launch

class SelectElectionPage(private val elections: List<Election>) : BasePage() {
    override val title: String = "Select Election"

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
                Spacer(modifier = Modifier.height(50.dp))

                ImageCarousel()

                Spacer(modifier = Modifier.height(50.dp))

                // Title
                Text(
                    text = "Select Your Election",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.Black, // Accent color
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // List of Voting Options with Cards
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    elections.forEach { election ->
                        Card(
                            onClick = { setCurrentPage(RegisterBasePage(election)) },
                            elevation = CardDefaults.cardElevation(6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = election.name,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color(0xFF4285F4),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Push bottom navigation to the bottom

                // Bottom Navigation
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { setCurrentPage(SelectElectionPage(sampleElections))}) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = Color.Black)
                    }
                    IconButton(onClick = {setCurrentPage(NotificationsPage(emptyList()))}) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { setCurrentPage(ProfilePage(UserData.sampleUser)) }) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "Profile", tint = Color.Black)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ImageCarousel() {
        // Define a list of drawable resource IDs for carousel images
        val images = listOf(
            R.drawable.img_1, // Replace with actual drawable IDs
            R.drawable.img_2,
            R.drawable.img_3
        )

        // Remember the pager state for controlling the carousel
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })
        val coroutineScope = rememberCoroutineScope()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Horizontal Pager for carousel
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Set height for the carousel
            ) { page ->
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "Carousel Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dots Indicator for carousel
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                images.forEachIndexed { index, _ ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 12.dp else 8.dp) // Slightly larger for the selected dot
                            .padding(horizontal = 4.dp)
                            .background(
                                color =  Color(0xFFC7C7C7),
                                shape = CircleShape // Ensures dots are circular
                            )
                    )
                }
            }

            // Auto-scroll functionality for carousel
            LaunchedEffect(pagerState) {
                while (true) {
                    kotlinx.coroutines.delay(3000) // 3-second delay between slides
                    coroutineScope.launch {
                        val nextPage = (pagerState.currentPage + 1) % images.size
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            }
        }
    }
}