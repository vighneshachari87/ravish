package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FitnessDarkColorScheme = darkColorScheme(
    primary = AthleticRed,
    onPrimary = OnAthleticRed,
    primaryContainer = AthleticRedContainer,
    onPrimaryContainer = AthleticRedLight,
    secondary = AthleticRedLight,
    onSecondary = Color.White,
    secondaryContainer = SurfaceCardElevated,
    onSecondaryContainer = TextPrimary,
    tertiary = GoldMilestone,
    onTertiary = Color.Black,
    background = BackgroundDeepBlack,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCardElevated,
    onSurfaceVariant = TextSecondary,
    outline = SurfaceBorder,
    outlineVariant = Color(0xFF38383E)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    // Premium dark fitness aesthetic is enforced for brand identity
    MaterialTheme(
        colorScheme = FitnessDarkColorScheme,
        typography = Typography,
        content = content
    )
}
