package com.example.votingapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Notification(
    val title: String,
    val description: String,
    val timestamp: String // E.g., "2 hours ago"
)

class NotificationsPage(private val notifications: List<Notification>) : BasePage() {
    override val title: String = "Notifications"

    @Composable
    fun Content(param: (BasePage) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4285F4)
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (notifications.isEmpty()) {
                // No Notifications Message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No notifications to show.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    )
                }
            } else {
                // Notifications List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(notifications) { notification ->
                        NotificationCard(notification)
                    }
                }
            }
        }
    }

    @Composable
    private fun NotificationCard(notification: Notification) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Notification Title
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4285F4)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Notification Description
                Text(
                    text = notification.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Timestamp
                Text(
                    text = notification.timestamp,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}
