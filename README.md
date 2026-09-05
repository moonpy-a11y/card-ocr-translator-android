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
- Android Studio (latest version recommended)
- Java 17+

## Setup

### 1. Clone Repository

```bash
git clone https://github.com/moonpy-a11y/card-ocr-translator-android.git
cd card-ocr-translator-android
```

### 2. Configure Google Cloud Credentials

#### Step 2a: Create a Google Cloud Project
1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project
3. Enable the following APIs:
   - **Vision API**: https://console.cloud.google.com/apis/library/vision.googleapis.com
   - **Translation API**: https://console.cloud.google.com/apis/library/translate.googleapis.com

#### Step 2b: Create Service Account
1. Navigate to **Service Accounts** in the Google Cloud Console
2. Create a new service account with name: `card-ocr-translator`
3. Grant the following roles:
   - Cloud Vision API Editor
   - Cloud Translation API Editor
4. Create a JSON key and download it

#### Step 2c: Add Credentials to Project
```bash
# Create assets directory
mkdir -p app/src/main/assets

# Copy your downloaded service account JSON file
cp ~/Downloads/service-account-key.json app/src/main/assets/google-credentials.json
```

**⚠️ IMPORTANT**: Add `google-credentials.json` to `.gitignore` (already included) to prevent accidental credential exposure.

### 3. Build & Run

#### Build Debug APK
```bash
# Build the app
./gradlew build

# Or for faster development builds
./gradlew assembleDebug
```

#### Run on Emulator
```bash
# Start Android emulator first, then:
./gradlew installDebug

# Or run directly
./gradlew run
```

#### Run on Physical Device
```bash
# Connect device via USB with debugging enabled
./gradlew installDebug

# View logs
./gradlew logcat
```

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/com/moonpy/cardocrtranslator/
│       │   ├── ui/
│       │   │   ├── screens/
│       │   │   │   ├── CameraScreen.kt          # Camera preview & capture UI
│       │   │   │   ├── ResultScreen.kt          # Display OCR results
│       │   │   │   └── SettingsScreen.kt        # Language & app settings
│       │   │   ├── navigation/
│       │   │   │   └── Navigation.kt            # App navigation logic
│       │   │   └── theme/
│       │   │       └── Theme.kt                 # Material Design 3 theming
│       │   ├── viewmodel/
│       │   │   └── OCRViewModel.kt              # MVVM ViewModel layer
│       │   ├── model/
│       │   │   ├── OCRResult.kt                 # Data classes
│       │   │   └── ProcessingState.kt           # State management
│       │   ├── repository/
│       │   │   ├── ImageRepository.kt           # Image processing logic
│       │   │   ├── VisionRepository.kt          # Vision API integration
│       │   │   └── TranslationRepository.kt     # Translation API integration
│       │   ├── di/
│       │   │   └── AppModule.kt                 # Hilt dependency injection
│       │   └── MainActivity.kt
│       ├── assets/
│       │   └── google-credentials.json          # Service account credentials
│       ├── res/
│       │   └── values/
│       │       └── strings.xml                  # App strings & resources
│       └── AndroidManifest.xml
├── build.gradle.kts
├── proguard-rules.pro
└── README.md
```

## Next Steps & Development Roadmap

### 🎯 Phase 1: Core Functionality (Current)
- ✅ Camera integration
- ✅ Image processing (flip/rotate)
- ✅ Vision API integration
- ✅ Translation API integration
- ✅ Basic UI with Compose
- ✅ Settings screen

### 📋 Phase 2: Enhanced Features (Upcoming)
- [ ] Gallery/Photo picker integration
- [ ] Batch processing (multiple images)
- [ ] Save results to local database
- [ ] Image annotation & editing before OCR
- [ ] Search history
- [ ] Favorites/bookmarks

### 🚀 Phase 3: Advanced Features (Future)
- [ ] Offline mode with ML Kit OCR fallback
- [ ] Cloud sync with Firebase
- [ ] Export to PDF/Word/CSV
- [ ] Handwriting recognition
- [ ] QR code detection
- [ ] Receipt/Invoice OCR templates
- [ ] API key management UI
- [ ] Dark theme toggle

### 🧪 Phase 4: Quality & Performance
- [ ] Comprehensive unit tests
- [ ] UI/Integration tests with Espresso
- [ ] Performance optimization
- [ ] Crash reporting (Firebase Crashlytics)
- [ ] Analytics integration
- [ ] Accessibility audit (TalkBack support)

### 📦 Phase 5: Distribution
- [ ] Release build optimization
- [ ] Publish to Google Play Store
- [ ] Beta testing program
- [ ] Release notes & documentation
- [ ] Support & feedback channels

## Development Workflow

### Running Tests
```bash
# Unit tests
./gradlew test

# Instrumented tests (on device/emulator)
./gradlew connectedAndroidTest
```

### Code Quality
```bash
# Run linter
./gradlew lint

# Format code
./gradlew ktlintFormat
```

### Build Release APK
```bash
# Create signed release build
./gradlew bundleRelease

# Or build release APK
./gradlew assembleRelease
```

## Permissions

The app requests the following Android permissions:

- `CAMERA`: Capture images from device camera
- `READ_EXTERNAL_STORAGE`: Access gallery photos
- `WRITE_EXTERNAL_STORAGE`: Save processed images
- `INTERNET`: Call Google Cloud APIs

## Troubleshooting

### Build Issues
```bash
# Clean and rebuild
./gradlew clean build

# Update dependencies
./gradlew dependencies --scan
```

### Common Errors

**"Failed to load credentials"**
- Ensure `google-credentials.json` exists in `app/src/main/assets/`
- Verify the JSON file format is valid

**"Vision API not enabled"**
- Check Google Cloud Console API enablement
- Verify service account has Vision API permissions

**"Camera permission denied"**
- Grant camera permission when prompted
- Check device Settings > Apps > Card OCR Translator > Permissions

### Debugging

Enable verbose logging:
```bash
# In Build Variant, select Debug
# Logs will appear in Logcat (Android Studio)
./gradlew logcat
```

## API Costs

⚠️ **Note**: This app uses Google Cloud APIs which have associated costs:

- **Vision API**: ~$1.50 per 1,000 requests
- **Translation API**: ~$15 per 1M characters

Estimate your usage at [Google Cloud Pricing Calculator](https://cloud.google.com/products/calculator)

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -am 'Add feature'`
4. Push branch: `git push origin feature/your-feature`
5. Submit Pull Request

## License

MIT License - See LICENSE file

## Author

**moonpy-a11y** - Accessibility-focused developer

## Related Projects

- [Card OCR Translator (Python CLI)](https://github.com/moonpy-a11y/card-ocr-translator) - Original command-line version
- [Google Cloud Vision API Docs](https://cloud.google.com/vision/docs)
- [Google Cloud Translation API Docs](https://cloud.google.com/translate/docs)

## Support

For issues, feature requests, or questions:
- 📝 Create an [Issue](https://github.com/moonpy-a11y/card-ocr-translator-android/issues)
- 💬 Start a [Discussion](https://github.com/moonpy-a11y/card-ocr-translator-android/discussions)
- 📧 Contact: [moonpy-a11y](https://github.com/moonpy-a11y)
