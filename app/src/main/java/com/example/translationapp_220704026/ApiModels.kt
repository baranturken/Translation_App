package com.example.translationapp_220704026

data class TranslationRequest(
    val text: String,
    val source_lang: String,
    val dest_lang: String
)

data class TranslationResponse(
    val translated_text: String
)
