package com.moonpy.cardocrtranslator.di

import android.content.Context
import com.moonpy.cardocrtranslator.repository.ImageRepository
import com.moonpy.cardocrtranslator.repository.TranslationRepository
import com.moonpy.cardocrtranslator.repository.VisionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideVisionRepository(
        @ApplicationContext context: Context
    ): VisionRepository {
        return VisionRepository(context)
    }

    @Singleton
    @Provides
    fun provideTranslationRepository(
        @ApplicationContext context: Context
    ): TranslationRepository {
        return TranslationRepository(context)
    }

    @Singleton
    @Provides
    fun provideImageRepository(): ImageRepository {
        return ImageRepository()
    }
}
