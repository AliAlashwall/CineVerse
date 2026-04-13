package com.example.cineverse

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.model.QuestionsListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json


sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class KtorViewModel : ViewModel() {

    private val _questions = MutableStateFlow<UiState<QuestionsListDto>>(UiState.Loading)
    val questions: StateFlow<UiState<QuestionsListDto>> = _questions

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }



     fun loadQuestions() {
        viewModelScope.launch {
            Log.d("Ktor", "Starting fetch...")
            try {
                val result = withContext(Dispatchers.IO) { fetchQuestions() }
                Log.d("Ktor", "Finished fetch: $result items")
                _questions.value = UiState.Success(result)
            } catch (e: Exception) {
                Log.e("Ktor", "Error", e)
                _questions.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    suspend fun fetchQuestions(): QuestionsListDto {
        return client.get("https://opentdb.com/api.php?amount=1").body()
    }
}