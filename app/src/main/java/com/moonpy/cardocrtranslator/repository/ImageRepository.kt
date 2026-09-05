package com.moonpy.cardocrtranslator.repository

import android.graphics.Bitmap
import android.graphics.Matrix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor() {

    suspend fun processImageBitmap(
        bitmap: Bitmap,
        flip: Boolean = true
    ): ByteArray = withContext(Dispatchers.Default) {
        try {
            var processedBitmap = bitmap

            // Apply horizontal flip if needed to correct mirror orientation
            if (flip) {
                val matrix = Matrix().apply {
                    postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
                }
                processedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
            }

            // Compress to JPEG bytes
            val outputStream = ByteArrayOutputStream()
            processedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            return@withContext outputStream.toByteArray()
        } catch (e: Exception) {
            throw Exception("Image Processing Error: ${e.message}", e)
        }
    }
}
