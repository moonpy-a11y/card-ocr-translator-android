# Google Cloud Libraries
-keep class com.google.cloud.** { *; }
-keep class com.google.auth.** { *; }
-keep class com.google.api.** { *; }
-dontwarn com.google.cloud.**
-dontwarn com.google.auth.**
-dontwarn com.google.api.**

# Protobuf
-keep class com.google.protobuf.** { *; }
-dontwarn com.google.protobuf.**

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# Retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

# Gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep interface dagger.hilt.** { *; }

# AndroidX
-keep class androidx.** { *; }
-dontwarn androidx.**

# Keep all Compose classes
-keep class androidx.compose.** { *; }
-keep interface androidx.compose.** { *; }
-dontwarn androidx.compose.**
