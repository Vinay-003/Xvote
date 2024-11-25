package com.example.votingapp

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.votingapp.ElectionData.sampleElections
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class Register : BasePage() {
    override val title: String = "Register"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var isTermsChecked by remember { mutableStateOf(false) }
        var phonenumber by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }


        val context = LocalContext.current
        val countryList = loadCountryList(context)
        var errors by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF4285F4),
            unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
            focusedLabelColor = Color(0xFF4285F4),
            unfocusedLabelColor = Color.Gray,
        )

        fun isValidEmail(email: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
            return email.matches(emailRegex)
        }

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val phoneRegex = "^[+]?[0-9]{10,13}$".toRegex()
            return phoneNumber.matches(phoneRegex)
        }

        fun validateInputs(): Boolean {
            val newErrors = mutableMapOf<String, String>()

            if (fullName.isBlank()) {
                newErrors["fullName"] = "Full Name is required"
            } else if (!isValidPhoneNumber(phonenumber)) {
                newErrors["phoneNumberInvalid"] = "Invalid Phone Number"
            } else if (country.isBlank()) {
                newErrors["country"] = "Country is required"
            }else if (!isTermsChecked) {
                newErrors["term check"] = "Accept the term and conditions"
            }else if (!countryList.contains(country)) {
                newErrors["countryInvalid"] = "Invalid Country Name"
            } else if (email.isBlank()) {
                newErrors["email"] = "Email is required"
            } else if (!isValidEmail(email)) {
                newErrors["emailInvalid"] = "Invalid email address"
            } else if (password.isBlank()) {
                newErrors["password"] = "Password is required"
            } else if (confirmPassword.isBlank()) {
                newErrors["confirmPassword"] = "Confirm Password is required"
            } else if (password != confirmPassword) {
                newErrors["passwordMismatch"] = "Passwords do not match"
            } else if (phonenumber.length <= 10) {
                newErrors["phone number"] = "PhoneNumber must be of 10 numbers"
            }

            errors = newErrors
            return newErrors.isEmpty()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle()
                            .copy(color = Color(0xFF4285F4))
                    ) {
                        append("R")
                    }
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle()
                            .copy(color = Color.Black)
                    ) {
                        append("egisteration")
                    }
                },
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["fullName"] != null
                )
                errors["fullName"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                OutlinedTextField(
                    value = phonenumber,
                    onValueChange = { phonenumber = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["phoneNumberInvalid"] != null
                )
                errors["phoneNumberInvalid"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                OutlinedTextField(
                    value = country,
                    onValueChange = { country = it },
                    label = { Text("Country") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["country"] != null || errors["countryInvalid"] != null
                )
                errors["country"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }
                errors["countryInvalid"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["email"] != null || errors["emailInvalid"] != null
                )
                errors["email"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
                errors["emailInvalid"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = textFieldColors,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["password"] != null
                )
                errors["password"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
                errors["passwordLength"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = textFieldColors,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["confirmPassword"] != null
                )
                errors["confirmPassword"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
                errors["passwordMismatch"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val termsAnnotatedString = buildAnnotatedString {
                pushStringAnnotation(tag = "TERMS", annotation = "terms_and_conditions")
                withStyle(style = SpanStyle(color = Color(0xFF4285F4), textDecoration = TextDecoration.Underline)) {
                    append("Terms and Conditions")
                }
                pop()
            }
            ClickableText(
                text = termsAnnotatedString,
                onClick = { offset ->
                    termsAnnotatedString.getStringAnnotations(tag = "TERMS", start = offset, end = offset)
                        .firstOrNull()?.let {
                            setCurrentPage(TermsAndConditions())
                        }
                },
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isTermsChecked,
                    onCheckedChange = { isTermsChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF4285F4))
                )
                Text(text = "I agree with Terms and Conditions")
                errors["term check"]?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (validateInputs()) {
                        setCurrentPage(SelectElectionPage(sampleElections))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))
            ) {
                Text("Register", fontSize = 16.sp, color = Color.White)
            }
        }
    }

    private fun loadCountryList(context: Context): List<String> {
        val inputStream = context.assets.open("countries.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(reader, type)
    }
}