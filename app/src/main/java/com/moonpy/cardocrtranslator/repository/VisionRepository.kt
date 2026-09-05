package com.moonpy.cardocrtranslator.repository

import android.content.Context
import com.google.cloud.vision.v1.AnnotateImageRequest
import com.google.cloud.vision.v1.AnnotateImageResponse
import com.google.cloud.vision.v1.Feature
import com.google.cloud.vision.v1.Feature.Type
import com.google.cloud.vision.v1.Image
import com.google.cloud.vision.v1.ImageAnnotatorClient
import com.google.cloud.vision.v1.ImageAnnotatorSettings
import com.google.protobuf.ByteString
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VisionRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var imageAnnotatorClient: ImageAnnotatorClient? = null

    private fun getClient(): ImageAnnotatorClient {
        if (imageAnnotatorClient == null) {
            val credentialsStream = context.assets.open("google-credentials.json")
            val settings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider { credentialsStream }
                .build()
            imageAnnotatorClient = ImageAnnotatorClient.create(settings)
        }
        return imageAnnotatorClient!!
    }

    suspend fun extractText(imageBytes: ByteArray): String = withContext(Dispatchers.IO) {
        try {
            val client = getClient()
            val img = Image.newBuilder()
                .setContent(ByteString.copyFrom(imageBytes))
                .build()

            val feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build()
            val req = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build()

            val response = client.batchAnnotateImages(listOf(req))
            val textAnnotations = response.responsesList.firstOrNull()?.textAnnotationsList

            if (textAnnotations.isNullOrEmpty()) {
                return@withContext ""
            }

            return@withContext textAnnotations[0].description
        } catch (e: Exception) {
            throw Exception("Vision API Error: ${e.message}", e)
        }
    }
}
