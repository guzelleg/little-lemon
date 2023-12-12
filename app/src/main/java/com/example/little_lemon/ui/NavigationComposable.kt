package com.example.little_lemon.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(context: Context, navController: NavHostController) {
    val startDestination = Onboarding.route

    NavHost(navController = navController, startDestination = startDestination){
        composable(Onboarding.route){
            Onboarding(context, navController)
        }
        composable(Home.route){
            Home()
        }
        composable(Profile.route){
            Profile(context, navController)
        }
    }

}