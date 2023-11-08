package com.example.simplelogin.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.simplelogin.data.LoginUserRequest
import com.example.simplelogin.service.UserService
import com.example.simplelogin.viewmodel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val userviewModel = UserViewModel(UserService.instance)

    val email = remember {
        mutableStateOf("jorge.flores@tec.mx")
    }

    val password = remember {
        mutableStateOf("1234")
    }

    val loginState = userviewModel.login.observeAsState()

    LaunchedEffect(key1 = loginState.value?.message) {
        loginState.value?.let {
            snackbarHostState.showSnackbar(it.message)
            navController.navigate("WelcomeScreenPage")
        }

    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }

    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text("Login", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            TextField(value = email.value, onValueChange = {
                email.value = it
            }, placeholder = {
                Text("Email")
            })

            TextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                placeholder = {
                    Text("Contraseña")
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            Button(onClick = {

                val user = LoginUserRequest(email.value, password.value)
                userviewModel.loginUser(user)

            }) {
                Text(text = "Ingresar")
            }

            Button(onClick = {
                navController.navigate("RegisterPage")
            }) {
                Text(text = "To Register")
            }
        }

    }
}
