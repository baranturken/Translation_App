package com.example.translationapp_220704026

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TranslationService {
    @POST("translate")
    suspend fun translateText(@Body request: TranslationRequest): Response<TranslationResponse>
}
