package com.example.cineverse.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cineverse.domain.model.RequestTokenResponseDTO

@Composable
fun TestScreen(viewModel: KtorViewModel) {

    LaunchedEffect(Unit) {
        viewModel.getTokenProcess()
    }
    val state by viewModel.tokenResponse.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state) {
            is UiState.Loading -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Text("Fetching data from API...") // Added text to confirm it's alive
                }
            }

            is UiState.Success -> {
                val data = (state as UiState.Success<RequestTokenResponseDTO>).data

                Column(){
                    Text("success: ${data.success}", modifier = Modifier.padding(8.dp))

                    Text("requestToken: ${data.requestToken}", modifier = Modifier.padding(8.dp))

                    Text("expiresAt: ${data.expiresAt}", modifier = Modifier.padding(8.dp))
                }
            }

            is UiState.Error -> {
                val message = (state as UiState.Error).message
                Text("Error: $message", color = Color.Red)
            }
        }
    }
}
