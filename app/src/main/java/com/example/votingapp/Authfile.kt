package com.example.votingapp
//
//import android.content.Context
//import android.net.Uri
//import java.net.HttpURLConnection
//import java.net.URL
//import java.nio.charset.StandardCharsets
//import org.json.JSONObject
//import android.widget.Toast
//
//fun signupWithEmailAndMetadata(
//    email: String,
//    password: String,
//    name: String,
//    phoneNumber: String,
//    country: String,
//    uidNumber: String,
//    uidProofUri: Uri?,
//    context: Context,
//    onSuccess: () -> Unit  // Callback function for success navigation
//) {
//    val supabaseUrl = "https://tavyxfbylyonfvjxgcsw.supabase.co"
//    val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRhdnl4ZmJ5bHlvbmZ2anhnY3N3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjg5MTM5NTksImV4cCI6MjA0NDQ4OTk1OX0.sDcRGw2amguiVnpXUODrhZJ4FbNObhFK9FBejgOP5jE"
//
//    try {
//        val url = URL(supabaseUrl)
//        val connection = url.openConnection() as HttpURLConnection
//        connection.requestMethod = "POST"
//        connection.setRequestProperty("Content-Type", "application/json")
//        connection.setRequestProperty("apikey", supabaseKey)
//        connection.doOutput = true
//
//        val body = JSONObject()
//        body.put("email", email)
//        body.put("password", password)
//
//        val metadata = JSONObject()
//        metadata.put("name", name)
//        metadata.put("phone_number", phoneNumber)
//        metadata.put("country", country)
//        metadata.put("uid_number", uidNumber)
//        metadata.put("uid_proof", uidProofUri.toString())//handle this correctly
//
//        body.put("data", metadata)
//
//        val outputStream = connection.outputStream
//        outputStream.write(body.toString().toByteArray(StandardCharsets.UTF_8))
//        outputStream.flush()
//        outputStream.close()
//
//        val responseCode = connection.responseCode
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            Toast.makeText(context, "Signup successful!", Toast.LENGTH_SHORT).show()
//            onSuccess()  // Call the success callback
//        } else {
//            Toast.makeText(context, "Error during signup: ${connection.responseMessage}", Toast.LENGTH_SHORT).show()
//        }
//    } catch (e: Exception) {
//        e.printStackTrace()
//        Toast.makeText(context, "Signup failed: ${e.message}", Toast.LENGTH_LONG).show()
//    }
//}
//
//fun loginWithEmail(
//    email: String,
//    password: String,
//    context: Context,
//    onSuccess: () -> Unit  // Callback function for success navigation
//) {
//    val supabaseUrl = "https://your-supabase-url.auth/v1/token?grant_type=password"
//    val supabaseKey = "your-supabase-api-key"
//
//    try {
//        val url = URL(supabaseUrl)
//        val connection = url.openConnection() as HttpURLConnection
//        connection.requestMethod = "POST"
//        connection.setRequestProperty("Content-Type", "application/json")
//        connection.setRequestProperty("apikey", supabaseKey)
//        connection.doOutput = true
//
//        val body = JSONObject()
//        body.put("email", email)
//        body.put("password", password)
//
//        val outputStream = connection.outputStream
//        outputStream.write(body.toString().toByteArray(StandardCharsets.UTF_8))
//        outputStream.flush()
//        outputStream.close()
//
//        val responseCode = connection.responseCode
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
//            onSuccess()  // Call the success callback
//        } else {
//            Toast.makeText(context, "Error during login: ${connection.responseMessage}", Toast.LENGTH_SHORT).show()
//        }
//    } catch (e: Exception) {
//        e.printStackTrace()
//        Toast.makeText(context, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
//    }
//}
//
