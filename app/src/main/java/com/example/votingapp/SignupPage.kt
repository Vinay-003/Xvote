package com.example.votingapp

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

@OptIn(ExperimentalMaterial3Api::class)
class SignupPage : BasePage() {
    override val title: String = "Sign Up"

    @Composable
    fun Content(sampleElections: List<Election>, setCurrentPage: (BasePage) -> Unit) {
        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var uidNumber by remember { mutableStateOf("") }
        var uidProof by remember { mutableStateOf<Uri?>(null) }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uidProof = uri
        }

        val context = LocalContext.current
        val countryList = loadCountryList(context)

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val phoneRegex = "^[+]?[0-9]{10,13}$".toRegex()
            return phoneNumber.matches(phoneRegex)
        }

        fun isValidCountry(country: String): Boolean {
            return countryList.contains(country)
        }

        // Dark theme colors for the card and content
        val backgroundColor = Color(0xFF1E1E1E)
        val cardColor = Color(0xFF2C2C2C)
        val textColor = Color.White
        val buttonColor = Color(0xFFBB86FC)

        Surface(color = backgroundColor, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Sign Up",
                            fontSize = 24.sp,
                            color = textColor
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Name field
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor,
                                cursorColor = textColor,
                                focusedBorderColor = buttonColor,
                                unfocusedBorderColor = Color.Gray,
//                                placeholderColor = Color.Gray
                            ),
                            placeholder = { Text("Enter your name", color = Color.Gray) }, // Define placeholder directly here

                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Phone number field
                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = { Text("Phone Number", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor,
                                cursorColor = textColor,
                                focusedBorderColor = buttonColor,
                                unfocusedBorderColor = Color.Gray,
//                                placeholderColor = Color.Gray
                            ),
                            placeholder = { Text("Enter your Phone number", color = Color.Gray) }, // Define placeholder directly here

                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Country field
                        OutlinedTextField(
                            value = country,
                            onValueChange = { country = it },
                            label = { Text("Country", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor,
                                cursorColor = textColor,
                                focusedBorderColor = buttonColor,
                                unfocusedBorderColor = Color.Gray,
//                                placeholderColor = Color.Gray
                            ),
                            placeholder = { Text("Enter your Country", color = Color.Gray) }, // Define placeholder directly here

                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // UID Number field
                        OutlinedTextField(
                            value = uidNumber,
                            onValueChange = { uidNumber = it },
                            label = { Text("UID Number", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor,
                                cursorColor = textColor,
                                focusedBorderColor = buttonColor,
                                unfocusedBorderColor = Color.Gray,
//                                placeholderColor = Color.Gray
                            ),
                            placeholder = { Text("Enter your UID number", color = Color.Gray) }, // Define placeholder directly here

                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { launcher.launch("image/*") }) {
                            Text("Upload UID Proof (Image)", color = textColor)
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        // Email field
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor,
                                cursorColor = textColor,
                                focusedBorderColor = buttonColor,
                                unfocusedBorderColor = Color.Gray,
//                                placeholderColor = Color.Gray
                            ),
                            placeholder = { Text("Enter your Email Id", color = Color.Gray) }, // Define placeholder directly here

                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Password field
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password", color = Color.Gray) },
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor,
                                cursorColor = textColor,
                                focusedBorderColor = buttonColor,
                                unfocusedBorderColor = Color.Gray,
//                                placeholderColor = Color.Gray
                            ),
                            placeholder = { Text("Enter your Password", color = Color.Gray) }, // Define placeholder directly here

                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Confirm Password field
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text("Confirm Password", color = Color.Gray) },
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor,
                                cursorColor = textColor,
                                focusedBorderColor = buttonColor,
                                unfocusedBorderColor = Color.Gray,
//                                placeholderColor = Color.Gray
                            ),
                            placeholder = { Text("Enter the Correct Password", color = Color.Gray) }, // Define placeholder directly here

                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                when {
                                    name.isEmpty() -> errorMessage = "Name is required"
                                    phoneNumber.isEmpty() -> errorMessage = "Phone Number is required"
                                    !isValidPhoneNumber(phoneNumber) -> errorMessage = "Invalid Phone Number"
                                    country.isEmpty() -> errorMessage = "Country is required"
                                    !isValidCountry(country) -> errorMessage = "Invalid Country Name"
                                    uidNumber.isEmpty() -> errorMessage = "UID Number is required"
                                    uidProof == null -> errorMessage = "UID Proof is required"
                                    email.isEmpty() -> errorMessage = "Email is required"
                                    password.isEmpty() -> errorMessage = "Password is required"
                                    confirmPassword.isEmpty() -> errorMessage = "Confirm Password is required"
                                    password != confirmPassword -> errorMessage = "Passwords do not match"
                                    password.length <= 6 -> errorMessage = "Password must be longer than 8 characters"
                                    else -> {
                                        setCurrentPage(SelectElectionPage(sampleElections))
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text("Sign Up", color = textColor)
                        }

                        errorMessage?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(it, color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }

    private fun loadCountryList(context: Context): List<String> {
        val inputStream = context.assets.open("countries.json")
        val reader = InputStreamReader(inputStream)
        val countryType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(reader, countryType)
    }
}
