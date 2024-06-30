package com.example.translationapp_220704026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.translationapp_220704026.ui.theme.TranslationApp_220704026Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslationApp_220704026Theme {
                // Here, we instantiate the TranslationViewModel specifically for use in this activity
                val translationViewModel = viewModel<TranslationViewModel>()
                HomeScreen(translationViewModel = translationViewModel)  // Using the HomeScreen Composable
            }
        }
    }
}

@Composable
fun DefaultPreview() {
    TranslationApp_220704026Theme {
        val translationViewModel = viewModel<TranslationViewModel>()  // Providing ViewModel for preview
        HomeScreen(translationViewModel = translationViewModel)  // Using the HomeScreen Composable in preview
    }
}