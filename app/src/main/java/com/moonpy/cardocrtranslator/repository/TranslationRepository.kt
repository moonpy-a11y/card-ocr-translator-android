package com.moonpy.cardocrtranslator.repository

import android.content.Context
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import com.google.cloud.translate.Translate.TranslateOption
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslationRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var translateClient: Translate? = null

    private fun getClient(): Translate {
        if (translateClient == null) {
            translateClient = TranslateOptions.getDefaultInstance().service
        }
        return translateClient!!
    }

    suspend fun translateText(text: String, targetLanguage: String): String = withContext(Dispatchers.IO) {
        try {
            if (text.isBlank()) {
                return@withContext ""
            }

            val client = getClient()
            val lines = text.split("\n").filter { it.trim().isNotEmpty() }

            val translations = lines.map { line ->
                val translation = client.translate(
                    line,
                    TranslateOption.targetLanguage(targetLanguage)
                )
                translation.translatedText
            }

            return@withContext translations.joinToString("\n")
        } catch (e: Exception) {
            throw Exception("Translation API Error: ${e.message}", e)
        }
    }
}
