package com.example.translationapp_220704026

import LanguageDropdown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    translationViewModel: TranslationViewModel // Ensure this is correctly defined in the ViewModel class
) {
    var text by remember { mutableStateOf("") }
    var sourceLanguage by remember { mutableStateOf("en") }
    var targetLanguage by remember { mutableStateOf("es") }
    var translatedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text to translate") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("From Language:")
        LanguageDropdown(
            languages = listOf("English", "Spanish", "French", "German", "Simplified Chinese", "Traditional Chinese"),
            selectedLanguage = sourceLanguage,
            onLanguageSelected = { sourceLanguage = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("To Language:")
        LanguageDropdown(
            languages = listOf("English", "Spanish", "French", "German", "Simplified Chinese", "Traditional Chinese"),
            selectedLanguage = targetLanguage,
            onLanguageSelected = { targetLanguage = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                translationViewModel.translateText(
                    text, sourceLanguage, targetLanguage,
                    onSuccess = { translatedText = it },
                    onError = { translatedText = "Error: $it" }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Translate")
        }

        if (translatedText.isNotEmpty()) {
            Text("Translation Result: $translatedText", Modifier.padding(top = 20.dp))
        }
    }
}
