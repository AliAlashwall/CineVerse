package com.example.cineverse

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.loginScreen.LoginScreen
import com.example.cineverse.presentation.loginScreen.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineVerseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val loginViewModel: LoginViewModel = hiltViewModel()
                    LoginScreen(
                        loginViewModel = loginViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}