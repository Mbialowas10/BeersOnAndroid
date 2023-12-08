package com.mbialowas.beersonandroid.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var auth: FirebaseAuth = Firebase.auth


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
                onValueChange = { password = it.trim() },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Error message display
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row {
                Button(onClick = {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Login successful, navigate to home screen
                                navController.navigate("home")
                            } else {
                                // Login failed, show error message
                                errorMessage =
                                    "Login failed: ${task.exception?.message ?: "Unknown error"}"
                            }
                        }
                }) {
                    Text("Login")
                }

                HyperlinkText(
                    text= "Don't have an account? Register here."
                ) {
                    navController.navigate("register")
                }
            }
            

            Text(errorMessage)
        }
    }
}



@Composable
fun HyperlinkText(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        color = Color.Blue,
        fontSize = 16.sp,
        modifier = Modifier.clickable { onClick() }
    )
}


