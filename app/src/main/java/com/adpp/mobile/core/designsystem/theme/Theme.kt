package com.adpp.mobile.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryEmerald,
    onPrimary = LightSurface,
    primaryContainer = PrimaryEmeraldLight,
    onPrimaryContainer = PrimaryEmeraldDark,
    secondary = AccentSky,
    onSecondary = LightSurface,
    secondaryContainer = AccentSkyLight,
    onSecondaryContainer = AccentSky,
    tertiary = AccentAmber,
    onTertiary = LightSurface,
    background = LightBackground,
    onBackground = TextHeading,
    surface = LightSurface,
    onSurface = TextHeading,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = LightBorder,
    outlineVariant = LightBorderSubtle,
    error = AccentRose,
    onError = LightSurface,
    errorContainer = AccentRoseLight,
    onErrorContainer = AccentRose
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkBackground,
    primaryContainer = DarkPrimaryLight,
    onPrimaryContainer = DarkPrimary,
    secondary = DarkPrimary,
    onSecondary = DarkBackground,
    secondaryContainer = DarkSurfaceVariant,
    onSecondaryContainer = DarkBody,
    background = DarkBackground,
    onBackground = DarkHeading,
    surface = DarkSurface,
    onSurface = DarkHeading,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkSecondary,
    outline = DarkBorder,
    error = AccentRose,
    onError = LightSurface
)

@Composable
fun ADPPTheme(
    darkTheme: Boolean = false, // Light Mode is default as requested
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                window.navigationBarColor = colorScheme.surface.toArgb()
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = !darkTheme
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
