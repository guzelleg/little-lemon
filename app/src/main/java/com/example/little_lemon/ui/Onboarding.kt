package com.example.little_lemon.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.little_lemon.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(context: Context, navHostController: NavHostController) {
    val  sharedPreferences = context.getSharedPreferences("0815", Context.MODE_PRIVATE)
    val scrollState = rememberScrollState()
    val firstName = remember {
        mutableStateOf("")
    }

    val lastName = remember {
        mutableStateOf("")
    }

    val email = remember {
        mutableStateOf("")
    }
    Column (
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(Modifier.fillMaxWidth(0.6f)) {
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo")
        }
        Row(modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(Color.Gray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Let's get to know you",
                color = Color.White,
                fontSize = 30.sp
                )
        }

        Text(text = "Personal Information",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),)
        OutlinedTextField(
            value = firstName.value,
            onValueChange ={
                           firstName.value = it
            },
            label = { Text(text = stringResource(id = R.string.firstName))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth())

        OutlinedTextField(
            value = lastName.value,
            onValueChange ={
                lastName.value = it
            },
            label = { Text(text = stringResource(id = R.string.lastName))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth())

        OutlinedTextField(
            value = email.value,
            onValueChange ={
                email.value = it
            },
            label = { Text(text = stringResource(id = R.string.email))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth())

        Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            onClick = {
                if (validateRegData(firstName.value, lastName.value, email.value))
                {
                    sharedPreferences.edit()
                        .putString("firstName", firstName.value)
                        .putString("lastName", lastName.value)
                        .putString("email", email.value)
                        .putBoolean("onBoarded", true)
                        .apply()

                    navHostController.navigate(Home.route){
                        popUpTo(Onboarding.route){inclusive = true}
                        launchSingleTop = true
                    }

                    Toast.makeText(context,
                        "Registration Successful",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                else{
                    Toast.makeText(context,
                        "Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_SHORT)
                        .show()
                }
               })
        {
            Text(text = "Register",
                color = Color.Gray
            )
        }

    }

}

@Preview
@Composable
fun OnBoardingPreview() {
    Onboarding(context = LocalContext.current, navHostController = NavHostController(LocalContext.current))
}

fun validateRegData(firstName:String, lastName: String, email: String) =
    firstName.isNotBlank() && lastName.isNotBlank() &&
            email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
