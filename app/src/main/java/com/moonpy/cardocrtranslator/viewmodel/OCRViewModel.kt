package com.moonpy.cardocrtranslator.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonpy.cardocrtranslator.model.OCRResult
import com.moonpy.cardocrtranslator.model.ProcessingState
import com.moonpy.cardocrtranslator.repository.ImageRepository
import com.moonpy.cardocrtranslator.repository.TranslationRepository
import com.moonpy.cardocrtranslator.repository.VisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OCRViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val visionRepository: VisionRepository,
    private val translationRepository: TranslationRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _processingState = MutableStateFlow<ProcessingState>(ProcessingState.Idle)
    val processingState: StateFlow<ProcessingState> = _processingState.asStateFlow()

    private val _targetLanguage = MutableStateFlow("en")
    val targetLanguage: StateFlow<String> = _targetLanguage.asStateFlow()

    fun setTargetLanguage(language: String) {
        _targetLanguage.value = language
    }

    fun processImage(bitmap: Bitmap, shouldFlip: Boolean = true) {
        viewModelScope.launch {
            try {
                _processingState.value = ProcessingState.Loading

                val startTime = System.currentTimeMillis()

                // Step 1: Process image (rotate, flip if needed)
                val processedBytes = imageRepository.processImageBitmap(
                    bitmap = bitmap,
                    flip = shouldFlip
                )

                // Step 2: Extract text using Vision API
                val extractedText = visionRepository.extractText(processedBytes)

                if (extractedText.isBlank()) {
                    _processingState.value = ProcessingState.Error("No text found in image")
                    return@launch
                }

                // Step 3: Translate text
                val translatedText = translationRepository.translateText(
                    text = extractedText,
                    targetLanguage = _targetLanguage.value
                )

                val processingTimeMs = System.currentTimeMillis() - startTime

                val result = OCRResult(
                    originalText = extractedText,
                    translatedText = translatedText,
                    sourceLanguage = "auto",
                    targetLanguage = _targetLanguage.value,
                    processingTimeMs = processingTimeMs,
                    imagePath = ""
                )

                _processingState.value = ProcessingState.Success(result)
            } catch (e: Exception) {
                _processingState.value = ProcessingState.Error(
                    message = e.message ?: "Unknown error occurred",
                    exception = e
                )
            }
        }
    }

    fun reset() {
        _processingState.value = ProcessingState.Idle
    }
}
