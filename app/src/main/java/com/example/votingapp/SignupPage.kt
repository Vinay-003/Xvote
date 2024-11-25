package com.example.votingapp

import android.content.Context
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
import com.example.votingapp.CandidateData.sampleCandidates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class SignupPage : BasePage() {
    override val title: String = "Sign Up"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(sampleElections: List<Election>, setCurrentPage: (BasePage) -> Unit) {
        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var uidNumber by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var isTermsChecked by remember { mutableStateOf(false) }

        var errors by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF4285F4),
            unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
        )

        val context = LocalContext.current
        val countryList = loadCountryList(context)

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val phoneRegex = "^[+]?[0-9]{10,13}$".toRegex()
            return phoneNumber.matches(phoneRegex)
        }

        fun validateInputs(): Boolean {
            val newErrors = mutableMapOf<String, String>()

            if (name.isBlank()) {
                newErrors["name"] = "Name is required"
            } else if (phoneNumber.isBlank()) {
                newErrors["phoneNumber"] = "Phone Number is required"
            } else if (!isValidPhoneNumber(phoneNumber)) {
                newErrors["phoneNumberInvalid"] = "Invalid Phone Number"
            } else if (country.isBlank()) {
                newErrors["country"] = "Country is required"
            } else if (!countryList.contains(country)) {
                newErrors["countryInvalid"] = "Invalid Country Name"
            } else if (uidNumber.isBlank()) {
                newErrors["uidNumber"] = "UID Number is required"
            } else if (email.isBlank()) {
                newErrors["email"] = "Email is required"
            } else if (password.isBlank()) {
                newErrors["password"] = "Password is required"
            } else if (confirmPassword.isBlank()) {
                newErrors["confirmPassword"] = "Confirm Password is required"
            } else if (password != confirmPassword) {
                newErrors["passwordMismatch"] = "Passwords do not match"
            } else if (password.length <= 6) {
                newErrors["passwordLength"] = "Password must be longer than 6 characters"
            } else if (!isTermsChecked) {
                newErrors["termsUnchecked"] = "You must agree to the Terms and Conditions"
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
            // Heading
            Text(
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.headlineSmall.toSpanStyle().copy(color = Color(0xFF4285F4))) {
                        append("S")
                    }
                    withStyle(style = MaterialTheme.typography.headlineSmall.toSpanStyle().copy(color = Color.Black)) {
                        append("ign Up")
                    }
                },
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Name Field
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["name"] != null
                )
                errors["name"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }

                // Phone Number Field
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    isError = errors["phoneNumber"] != null || errors["phoneNumberInvalid"] != null
                )
                errors["phoneNumber"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }
                errors["phoneNumberInvalid"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }

                // Country Field
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

                // UID Number Field
                OutlinedTextField(
                    value = uidNumber,
                    onValueChange = { uidNumber = it },
                    label = { Text("UID Number") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["uidNumber"] != null
                )
                errors["uidNumber"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }

                // Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    isError = errors["email"] != null
                )
                errors["email"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }

                // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["password"] != null
                )
                errors["password"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }
                errors["passwordLength"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }

                // Confirm Password Field
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = textFieldColors,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    isError = errors["confirmPassword"] != null || errors["passwordMismatch"] != null
                )
                errors["confirmPassword"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }
                errors["passwordMismatch"]?.let { Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Terms and Conditions
            val termsAnnotatedString = buildAnnotatedString {
                pushStringAnnotation(tag = "TERMS", annotation = "terms_and_conditions")
                withStyle(style = SpanStyle(color = Color(0xFF4285F4), textDecoration = TextDecoration.Underline)) {
                    append("Terms and Conditions")
                }
                pop()
            }
            ClickableText(
                text = termsAnnotatedString,
                onClick = { setCurrentPage(TermsAndConditions()) },
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Checkbox for Terms
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isTermsChecked,
                    onCheckedChange = { isTermsChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF4285F4))
                )
                Text("I agree with Terms and Conditions")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up Button
            Button(
                onClick = {
                    if (validateInputs()) {
                        setCurrentPage(SelectCandidatePage(sampleCandidates))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))
            ) {
                Text("Sign Up", fontSize = 16.sp, color = Color.White)
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