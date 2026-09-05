# Card OCR Translator - Android

Android native application for digitizing and translating mirrored or improperly oriented text images from study cards using Google Cloud Vision and Translation APIs.

## Features

- **Camera Integration**: Capture images directly from device camera or select from gallery
- **Image Processing**: Auto-correct orientation and mirroring for optimal OCR
- **Text Recognition**: Extract text using Google Cloud Vision API
- **Multi-language Translation**: Translate extracted text using Google Cloud Translation API
- **Real-time Results**: Display original and translated text side-by-side
- **Performance Metrics**: Track API processing latency
- **Accessibility**: Built with a11y principles

## Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM with Repository Pattern
- **UI Framework**: Jetpack Compose & Android Material Design 3
- **Image Processing**: Glide, Bitmap utilities
- **Cloud APIs**: Google Cloud Vision, Google Cloud Translation
- **Async**: Coroutines & Flow
- **Dependency Injection**: Hilt

## Requirements

- Android 8.0+ (API 26)
- Google Cloud Project with Vision and Translation APIs enabled
- Service Account credentials (JSON)

## Setup

### 1. Clone Repository

```bash
git clone https://github.com/moonpy-a11y/card-ocr-translator-android.git
cd card-ocr-translator-android
```

### 2. Configure Google Cloud Credentials

```bash
# Place your Google Cloud service account JSON file
mkdir -p app/src/main/assets
cp path/to/service-account.json app/src/main/assets/google-credentials.json
```

### 3. Build & Run

```bash
# Build debug APK
./gradlew build

# Run on emulator or device
./gradlew installDebug
```

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/com/moonpy/cardocrtranslator/
│       │   ├── ui/
│       │   │   ├── screens/
│       │   │   │   ├── CameraScreen.kt
│       │   │   │   ├── ResultScreen.kt
│       │   │   │   └── SettingsScreen.kt
│       │   │   ├── components/
│       │   │   │   ├── ProcessingIndicator.kt
│       │   │   │   └── ResultDisplay.kt
│       │   │   └── theme/
│       │   │       └── Theme.kt
│       │   ├── viewmodel/
│       │   │   └── OCRViewModel.kt
│       │   ├── model/
│       │   │   ├── OCRResult.kt
│       │   │   └── ProcessingState.kt
│       │   ├── repository/
│       │   │   ├── ImageRepository.kt
│       │   │   ├── VisionRepository.kt
│       │   │   └── TranslationRepository.kt
│       │   ├── di/
│       │   │   └── AppModule.kt
│       │   └── MainActivity.kt
│       ├── assets/
│       │   └── google-credentials.json
│       └── AndroidManifest.xml
├── build.gradle.kts
└── proguard-rules.pro
```

## License

MIT License - See LICENSE file

## Author

**moonpy-a11y** - Accessibility-focused developer

## Related

- [Card OCR Translator (Python CLI)](https://github.com/moonpy-a11y/card-ocr-translator)
