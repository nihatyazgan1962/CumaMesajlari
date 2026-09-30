package com.cumamesajlari.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val GoldPrimary = Color(0xFFD4AF37)
val GoldLight = Color(0xFFFFE082)
val EmeraldDark = Color(0xFF0D5C46)
val EmeraldDeep = Color(0xFF042B1F)
val NavyDark = Color(0xFF0B1426)
val SurfaceDark = Color(0xFF131D31)
val CardSurfaceDark = Color(0xFF1A263D)

private val DarkColorScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = Color(0xFF1A1A1A),
    primaryContainer = EmeraldDark,
    onPrimaryContainer = GoldLight,
    secondary = Color(0xFF64B5F6),
    onSecondary = Color(0xFF001F3F),
    background = NavyDark,
    onBackground = Color(0xFFF0F4F8),
    surface = SurfaceDark,
    onSurface = Color(0xFFF0F4F8),
    surfaceVariant = CardSurfaceDark,
    onSurfaceVariant = Color(0xFFD1D8E0)
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldDark,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC8E6C9),
    onPrimaryContainer = EmeraldDeep,
    secondary = GoldPrimary,
    onSecondary = Color(0xFF2C2200),
    background = Color(0xFFF8FAF9),
    onBackground = Color(0xFF191C1B),
    surface = Color.White,
    onSurface = Color(0xFF191C1B),
    surfaceVariant = Color(0xFFE8F5E9),
    onSurfaceVariant = Color(0xFF3E4944)
)

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)

@Composable
fun CumaMesajlariTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else DarkColorScheme // Islamic aesthetic excels in rich dark/gold palette

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
