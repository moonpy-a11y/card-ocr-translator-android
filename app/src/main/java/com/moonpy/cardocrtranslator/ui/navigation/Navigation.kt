package com.moonpy.cardocrtranslator.ui.navigation

import android.graphics.Bitmap
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moonpy.cardocrtranslator.model.OCRResult
import com.moonpy.cardocrtranslator.model.ProcessingState
import com.moonpy.cardocrtranslator.ui.screens.CameraScreen
import com.moonpy.cardocrtranslator.ui.screens.ResultScreen
import com.moonpy.cardocrtranslator.ui.screens.SettingsScreen

seal class Screen(val route: String) {
    data object Camera : Screen("camera")
    data object Result : Screen("result")
    data object Settings : Screen("settings")
}

@Composable
fun CardOCRNavGraph() {
    val navController = rememberNavController()
    var currentBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var currentResult by remember { mutableStateOf<OCRResult?>(null) }
    var processingState by remember { mutableStateOf<ProcessingState>(ProcessingState.Idle) }

    NavHost(
        navController = navController,
        startDestination = Screen.Camera.route
    ) {
        composable(Screen.Camera.route) {
            CameraScreen(
                onImageCaptured = { bitmap ->
                    currentBitmap = bitmap
                    navController.navigate(Screen.Result.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(Screen.Result.route) {
            if (currentResult != null) {
                ResultScreen(
                    result = currentResult!!,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
