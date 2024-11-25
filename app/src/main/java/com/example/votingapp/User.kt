package com.example.votingapp

data class User(
    val name: String,           // User's full name
    val phoneNumber: String,    // User's phone number
    val country: String,        // User's country of residence
    val uidNumber: String,      // User's unique identification number
    val email: String           // User's email address
)

object UserData {
    val sampleUser = User(
        name = "John Doe",
        phoneNumber = "123-456-7890",
        country = "USA",
        uidNumber = "UID123456",
        email = "john.doe@example.com"
    )
}