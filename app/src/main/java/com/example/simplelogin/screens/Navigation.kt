package com.example.simplelogin.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "RegisterPage" )
    {

        composable("RegisterPage"){
            RegisterPage(navController)
        }
        // al estilo web
        composable("LoginPage"){
           LoginPage(navController)
        }

        composable("WelcomeScreenPage"){
            WelcomeScreenPage()
        }
    }
}