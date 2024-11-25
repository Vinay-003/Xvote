package com.example.votingapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import kotlinx.coroutines.launch

class HomePage : BasePage() {
    override val title: String = "Welcome"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        // Light theme background setup
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6)) // Light gray background
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image Carousel
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Welcome Image",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(16.dp)

                )

                Spacer(modifier = Modifier.height(24.dp))

                // Welcome Text
                Text(
                    text = "Welcome to Our App",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4285F4) // Theme color
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ){

                Text(
                    text = "Join us to experience a seamless voting experience.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF616161) // Secondary text color
                    ),
                    fontSize = 16.sp
                )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = { setCurrentPage(LoginPage()) },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))
                ) {
                    Text("Login", color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Register Button
                OutlinedButton(
                    onClick = { setCurrentPage(Register()) },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF4285F4)),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
                ) {
                    Text("Register", color = Color(0xFF4285F4), fontSize = 16.sp)
                }
            }
        }
    }
}
