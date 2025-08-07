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
import androidx.core.view.WindowCompat // Menggunakan WindowCompat yang lebih direkomendasikan
import androidx.compose.ui.tooling.preview.Preview

// =============================================================================
// DEFINISIKAN COLOR SCHEME BARU DENGAN WARNA DARI Color.kt
// PASTIKAN WARNA SEPERTI MyPrimaryBlue, MyLightGrayBackground, dll. SUDAH ADA DI Color.kt
// =============================================================================

// Contoh Light Color Scheme yang dimodifikasi
private val LightColorScheme = lightColorScheme(
    primary = MyPrimaryBlue, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onPrimary = Color.White, // Warna teks/ikon di atas primary
    primaryContainer = MyPrimaryBlue.copy(alpha = 0.2f), // Varian warna primary
    onPrimaryContainer = Color.Black, // Warna teks/ikon di atas primaryContainer
    secondary = MyAccentGreen, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onSecondary = Color.White, // Warna teks/ikon di atas secondary
    secondaryContainer = MyAccentGreen.copy(alpha = 0.2f), // Varian warna secondary
    onSecondaryContainer = Color.Black, // Warna teks/ikon di atas secondaryContainer
    tertiary = Pink40, // Bisa tetap pakai yang lama atau ganti dengan warna kustom
    onTertiary = Color.White,
    tertiaryContainer = Pink40.copy(alpha = 0.2f),
    onTertiaryContainer = Color.Black,
    background = MyLightGrayBackground, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onBackground = Color.Black, // Warna teks/ikon di atas background
    surface = MyLightGrayBackground, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onSurface = Color.Black, // Warna teks/ikon di atas surface
    surfaceVariant = Color.LightGray, // Varian warna surface
    onSurfaceVariant = Color.DarkGray, // Warna teks/ikon di atas surfaceVariant
    error = Color.Red, // Warna untuk error
    onError = Color.White,
    errorContainer = Color.Red.copy(alpha = 0.2f),
    onErrorContainer = Color.Black,
    outline = Color.Gray, // Warna untuk outline elemen
    // Tambahkan properti warna lainnya sesuai kebutuhan Material Design 3
)

// Contoh Dark Color Scheme yang dimodifikasi
private val DarkColorScheme = darkColorScheme(
    primary = MyPrimaryBlue, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onPrimary = Color.Black,
    primaryContainer = MyPrimaryBlue.copy(alpha = 0.4f),
    onPrimaryContainer = Color.White,
    secondary = MyAccentGreen, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onSecondary = Color.Black,
    secondaryContainer = MyAccentGreen.copy(alpha = 0.4f),
    onSecondaryContainer = Color.White,
    tertiary = Pink80, // Bisa tetap pakai yang lama atau ganti dengan warna kustom
    onTertiary = Color.Black,
    tertiaryContainer = Pink80.copy(alpha = 0.4f),
    onTertiaryContainer = Color.White,
    background = MyDarkGrayBackground, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onBackground = Color.White,
    surface = MyDarkGrayBackground, // Menggunakan warna kustom baru (pastikan ada di Color.kt)
    onSurface = Color.White,
    surfaceVariant = Color(0xFF424242),
    onSurfaceVariant = Color.LightGray,
    error = Color.Red,
    onError = Color.Black,
    errorContainer = Color.Red.copy(alpha = 0.4f),
    onErrorContainer = Color.White,
    outline = Color.DarkGray,
    // Tambahkan properti warna lainnya sesuai kebutuhan Material Design 3
)
// =============================================================================


@Composable
fun DoseAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available only on Android 12+
    dynamicColor: Boolean = true, // Ubah ke false jika tidak ingin dynamic color
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
            // Menggunakan WindowCompat untuk kontrol status bar yang lebih baik
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Pastikan Typography sudah didefinisikan di Type.kt
        content = content
    )
}
