package com.example.votingapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TermsAndConditions : BasePage() {
    override val title: String = "Terms and Conditions"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Title
            Text(
                text = "Terms and Conditions",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A148C),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Terms Content
            Text(
                text = """
                    Welcome to [App Name]! By using this app, you agree to the following terms and conditions:
                    
                    1. **Usage of the App**:
                       - You must be 18 years or older to use this application.
                       - The app is intended for voting purposes only and must not be used for any illegal activity.
                    
                    2. **Data Privacy**:
                       - We are committed to protecting your personal data.
                       - Any information you provide will be securely encrypted and will not be shared with third parties without your consent.
                    
                    3. **Account Responsibility**:
                       - You are responsible for maintaining the confidentiality of your account details.
                       - Notify us immediately of any unauthorized access to your account.
                    
                    4. **Termination**:
                       - We reserve the right to terminate your account if you violate these terms.
                    
                    5. **Changes to Terms**:
                       - These terms are subject to change at any time. Continued use of the app implies your acceptance of the updated terms.
                    
                    Thank you for using [App Name]. If you have any questions, please contact support at support@appname.com.
                """.trimIndent(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Back Button
            Button(
                onClick = { setCurrentPage(Register()) },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A148C))
            ) {
                Text("Back to Registration", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
