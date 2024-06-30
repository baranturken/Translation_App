package com.example.translationapp_220704026

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslationManager {

    private var translator: Translator? = null

    fun setupTranslator(sourceLang: String, targetLang: String) {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.fromLanguageTag(sourceLang) ?: TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.fromLanguageTag(targetLang) ?: TranslateLanguage.SPANISH)
            .build()
        translator = Translation.getClient(options)
    }

    fun downloadModel(onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        translator?.downloadModelIfNeeded(conditions)
            ?.addOnSuccessListener { onSuccess() }
            ?.addOnFailureListener { onFailure(it) }
    }

    fun translateText(inputText: String, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        translator?.translate(inputText)
            ?.addOnSuccessListener { onSuccess(it) }
            ?.addOnFailureListener { onFailure(it) }
    }

    fun closeTranslator() {
        translator?.close()
    }
}
