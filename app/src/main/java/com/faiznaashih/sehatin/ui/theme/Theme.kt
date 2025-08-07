package com.waseefakhtar.doseapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text

// ================================
// DEFINISI WARNA UNTUK LIGHT MODE
// ================================
private val LightColorScheme = lightColorScheme(
    primary = MyPrimaryBlue,
    onPrimary = Color.White,
    primaryContainer = MyPrimaryBlue.copy(alpha = 0.2f),
    onPrimaryContainer = Color.Black,
    secondary = MyAccentGreen,
    onSecondary = Color.White,
    secondaryContainer = MyAccentGreen.copy(alpha = 0.2f),
    onSecondaryContainer = Color.Black,
    tertiary = Pink40,
    onTertiary = Color.White,
    tertiaryContainer = Pink40.copy(alpha = 0.2f),
    onTertiaryContainer = Color.Black,
    background = MyLightGrayBackground,
    onBackground = Color.Black,
    surface = MyLightGrayBackground,
    onSurface = Color.Black,
    surfaceVariant = Color.LightGray,
    onSurfaceVariant = Color.DarkGray,
    error = Color.Red,
    onError = Color.White,
    errorContainer = Color.Red.copy(alpha = 0.2f),
    onErrorContainer = Color.Black,
    outline = Color.Gray
)

// ================================
// DEFINISI WARNA UNTUK DARK MODE
// ================================
private val DarkColorScheme = darkColorScheme(
    primary = MyPrimaryBlue,
    onPrimary = Color.Black,
    primaryContainer = MyPrimaryBlue.copy(alpha = 0.4f),
    onPrimaryContainer = Color.White,
    secondary = MyAccentGreen,
    onSecondary = Color.Black,
    secondaryContainer = MyAccentGreen.copy(alpha = 0.4f),
    onSecondaryContainer = Color.White,
    tertiary = Pink80,
    onTertiary = Color.Black,
    tertiaryContainer = Pink80.copy(alpha = 0.4f),
    onTertiaryContainer = Color.White,
    background = MyDarkGrayBackground,
    onBackground = Color.White,
    surface = MyDarkGrayBackground,
    onSurface = Color.White,
    surfaceVariant = Color(0xFF424242),
    onSurfaceVariant = Color.LightGray,
    error = Color.Red,
    onError = Color.Black,
    errorContainer = Color.Red.copy(alpha = 0.4f),
    onErrorContainer = Color.White,
    outline = Color.DarkGray
)

// ================================
// THEME UTAMA APLIKASI
// ================================
@Composable
fun DoseAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// ================================
// PREVIEW UNTUK COMPOSE PREVIEW
// ================================
@Preview(showBackground = true)
@Composable
fun PreviewDoseAppTheme() {
    DoseAppTheme {
        Text("Halo dari Preview!")
    }
}
