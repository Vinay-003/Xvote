package com.example.votingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.blur

class LoginPage : BasePage() {
    override val title: String = "Login"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(sampleElections: List<Election>, setCurrentPage: (BasePage) -> Unit) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var rememberMe by remember { mutableStateOf(false) }

        val context = LocalContext.current

        fun isValidEmail(email: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
            return email.matches(emailRegex)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient( // Light gradient background
                    colors = listOf(Color(0xFFF6F6F6), Color(0xFFE8E8E8))
                ))
        ) {
            // Left side glassmorphism effect
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(60.dp)
                    .align(Alignment.CenterStart)
                    .graphicsLayer {
                        alpha = 0.5f
                    }
                    .blur(radius = 16.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.3f), Color.White.copy(alpha = 0.1f))
                        )
                    )
            )

            // Right side glassmorphism effect
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(60.dp)
                    .align(Alignment.CenterEnd)
                    .graphicsLayer {
                        alpha = 0.5f
                    }
                    .blur(radius = 16.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.3f), Color.White.copy(alpha = 0.1f))
                        )
                    )
            )

            // Center Card
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White // Simple card without translucency
                ),
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Profile icon placeholder
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFFE8E8E8), CircleShape), // Light gray circle
                        contentAlignment = Alignment.Center
                    ) {
                        Text("👤", fontSize = 32.sp, color = Color(0xFF555555)) // Dark gray icon text
                    }

                    // Login text in dark gray
                    Text(
                        text = "Login",
                        fontSize = 24.sp,
                        color = Color(0xFF333333) // Dark gray color
                    )

                    // Email field
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email", color = Color.Gray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color(0xFF4285F4), // Theme color
                            focusedBorderColor = Color(0xFF4285F4),
                            unfocusedBorderColor = Color.Gray
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black), // Set the text color to black
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Password field
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password", color = Color.Gray) },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color(0xFF4285F4),
                            focusedBorderColor = Color(0xFF4285F4),
                            unfocusedBorderColor = Color.Gray
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black), // Set the text color to black
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Error message display
                    errorMessage?.let {
                        Text(it, color = Color(0xFFFF6F61)) // Light red error message
                    }

                    // Login button
                    Button(
                        onClick = {
                            if (email.isEmpty() || password.isEmpty()) {
                                errorMessage = "Email and Password are required."
                            } else if (!isValidEmail(email)) {
                                errorMessage = "Please enter a valid email address."
                            } else {
                                setCurrentPage(SelectElectionPage(sampleElections)) // Placeholder navigation
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)) // Theme color for button
                    ) {
                        Text("Login", color = Color.White)
                    }

                    // Remember Me and Forgot Password section
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = rememberMe,
                                onCheckedChange = { rememberMe = it },
                                colors = CheckboxDefaults.colors(
                                    checkmarkColor = Color.White,
                                    uncheckedColor = Color.Gray,
                                    checkedColor = Color(0xFF4285F4) // Theme color for checkbox
                                )
                            )
                            Text(text = "Remember", fontSize = 14.sp, color = Color.Gray)
                        }
                        TextButton(
                            onClick = { /* Handle forgot password click */ },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFF4285F4)) // Theme color for text button
                        ) {
                            Text("Forgot password?", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
