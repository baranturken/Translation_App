package com.example.translationapp_220704026

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TranslationViewModel : ViewModel() {

    fun translateText(
        text: String,
        sourceLang: String,
        destLang: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.translateText(TranslationRequest(text, sourceLang, destLang))
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!.translated_text)
                } else {
                    onError(response.message())
                }
            } catch (e: Exception) {
                onError(e.message ?: "An error occurred")
            }
        }
    }
}
