package com.example.votingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                .background(Color(0xFF121212)), // Dark background color
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)), // Dark card color
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Profile icon placeholder with dark color
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFF333333), CircleShape), // Darker circle color
                        contentAlignment = Alignment.Center
                    ) {
                        Text("👤", fontSize = 32.sp, color = Color.White) // White icon text
                    }

                    // Login text in white
                    Text(
                        text = "Login",
                        fontSize = 24.sp,
                        color = Color.White
                    )

                    // Email field with dark colors
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email", color = Color.Gray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.White,       // Set input text color when focused to white
                            unfocusedTextColor = Color.White,     // Set input text color when unfocused to white
                            cursorColor = Color.White,
                            focusedBorderColor = Color(0xFFBB86FC), // Light purple color for focused border
                            unfocusedBorderColor = Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

// Password field with dark colors
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password", color = Color.Gray) },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.White,       // Set input text color when focused to white
                            unfocusedTextColor = Color.White,     // Set input text color when unfocused to white
                            cursorColor = Color.White,
                            focusedBorderColor = Color(0xFFBB86FC),
                            unfocusedBorderColor = Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )


                    // Error message display
                    errorMessage?.let {
                        Text(it, color = Color(0xFFFF6F61)) // Error color in light red
                    }

                    // Login button with dark theme colors
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
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB86FC)) // Light purple button color
                    ) {
                        Text("Login", color = Color.White)
                    }

                    // Remember Me and Forgot Password section with dark colors
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
                                    checkedColor = Color(0xFFBB86FC) // Light purple for checked color
                                )
                            )
                            Text(text = "Remember", fontSize = 14.sp, color = Color.Gray)
                        }
                        TextButton(
                            onClick = { /* Handle forgot password click */ },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFBB86FC)) // Light purple text color
                        ) {
                            Text("Forgot password?", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}