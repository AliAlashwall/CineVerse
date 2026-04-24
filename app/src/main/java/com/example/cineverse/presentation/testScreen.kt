package com.example.cineverse.presentation

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cineverse.domain.model.RequestTokenResponseDTO

@Composable
fun TestScreen(viewModel: KtorViewModel) {
    val tokenResponseState by viewModel.tokenResponse.collectAsStateWithLifecycle()

    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()

//    LaunchedEffect(key1 = Unit) {
//            viewModel.getTokenProcess()
//    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            when (tokenResponseState) {
                is UiState.Loading -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Text("Fetching data from API...") // Added text to confirm it's alive
                    }
                }

                is UiState.Success -> {
                    val data = (tokenResponseState as UiState.Success<RequestTokenResponseDTO>).data

                    Column() {
                        Text("success: ${data.success}", modifier = Modifier.padding(8.dp))

                        Text(
                            "requestToken: ${data.requestToken}",
                            modifier = Modifier.padding(8.dp)
                        )

                        Text("expiresAt: ${data.expiresAt}", modifier = Modifier.padding(8.dp))

                        val context = LocalContext.current

                        Button(onClick = {
                            val url =
                                "https://www.themoviedb.org/authenticate/${authUiState.accessToken}?redirect_to=myapp://approved"


                            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                            context.startActivity(intent)

                            /*
                            val customTabsIntent = CustomTabsIntent.Builder().build()
                            customTabsIntent.launchUrl(context, Uri.parse(url))
                            * */
                        }) {
                            Text("Login with TMDB")
                        }


                    }
                }

                is UiState.Error -> {
                    val message = (tokenResponseState as UiState.Error).message
                    Text("Error: $message", color = Color.Red)
                }


            }
            Button(onClick = {
                viewModel.login("po2378", "po2378")
            }) {
                Text("Login")
            }
        }
    }
}
