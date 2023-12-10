package com.example.little_lemon.ui

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route = "Home"
}

object Onboarding : Destinations {
    override val route = "OnBoarding"
}

object Profile : Destinations {
    override val route = "Profile"
}