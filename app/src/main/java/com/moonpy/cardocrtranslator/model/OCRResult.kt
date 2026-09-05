package com.moonpy.cardocrtranslator.model

data class OCRResult(
    val originalText: String,
    val translatedText: String,
    val sourceLanguage: String,
    val targetLanguage: String,
    val processingTimeMs: Long,
    val imagePath: String
)

sealed class ProcessingState {
    data object Idle : ProcessingState()
    data object Loading : ProcessingState()
    data class Success(val result: OCRResult) : ProcessingState()
    data class Error(val message: String, val exception: Exception? = null) : ProcessingState()
}
