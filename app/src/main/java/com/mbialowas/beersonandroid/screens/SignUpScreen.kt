package com.mbialowas.beersonandroid.screens

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.common.io.Files.append
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.mbialowas.beersonandroid.navigation.BottomNavBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage: String by remember { mutableStateOf("") }
    var successMessage: String by remember { mutableStateOf("") }
    var auth: FirebaseAuth = Firebase.auth
    var TAG = "MJB"

    val coroutineScope = rememberCoroutineScope()

    Column {
        // Email and password input fields
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Email input field
            TextField(
                value = email,
                onValueChange = { email = it.trim() },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password input field
            TextField(
                value = password,
                onValueChange = { newPassword ->
                    password = newPassword.trim()
                    errorMessage = validatePassword(newPassword)
                },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Error message display
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )

            // Success message display
            if (successMessage.isNotEmpty()) {
                Text(
                    text = successMessage,
                    color = Color.Blue,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Row {
                Button(onClick = {
                    createUserWithEmailAndPassword(email,password,
                        successMessageSetter = { successMessage = it },
                        errorMessageSetter = { errorMessage = it }
                    )
                    val user = Firebase.auth.currentUser
                    coroutineScope.launch {
                        // Introduce a delay (e.g., 2 seconds) before navigating
                        delay(2000)
                        val user = Firebase.auth.currentUser
                        if (user != null) {
                            navController.navigate("login")
                        }
                    }
                }

                ) {
                    Text("Register") // change text from Login to Register
                }

            }
            Text(errorMessage)
        }
    }
}

fun validatePassword(newPassword: String): String {
    return if (newPassword.length < 8) {
        "Password must be at least 8 characters long."
    } else {
        ""
    }
}

fun createUserWithEmailAndPassword(
    email: String,
    password: String,
    successMessageSetter: (String) -> Unit,
    errorMessageSetter: (String) -> Unit
) {
    Firebase.auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                successMessageSetter("Account created successfully! You can now log in.")
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                errorMessageSetter("Failed to create an account: ${task.exception?.message ?: "Unknown error"}")
            }
        }
}



