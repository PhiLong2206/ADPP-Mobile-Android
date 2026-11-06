package com.adpp.mobile.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight

@Composable
fun ADPPAvatar(
    name: String = "",
    initials: String = "",
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    showBorder: Boolean = false,
    borderColor: Color = PrimaryEmerald,
    isOnline: Boolean = false
) {
    val displayInitials = when {
        initials.isNotBlank() -> initials.take(2).uppercase()
        name.isNotBlank() -> name.replace(Regex("[()]"), "").trim().split(" ")
            .filter { it.isNotBlank() }
            .takeLast(2)
            .map { it.first().uppercaseChar() }
            .joinToString("")
            .ifEmpty { "U" }
        else -> "U"
    }

    Box(modifier = modifier.size(size)) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .then(
                    if (showBorder) Modifier.border(1.5.dp, borderColor, CircleShape)
                    else Modifier
                )
                .background(PrimaryEmeraldLight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = displayInitials,
                color = PrimaryEmerald,
                fontWeight = FontWeight.Bold,
                fontSize = (size.value * 0.38f).sp
            )
        }

        if (isOnline) {
            Box(
                modifier = Modifier
                    .size(size * 0.28f)
                    .clip(CircleShape)
                    .background(PrimaryEmerald)
                    .border(2.dp, Color.White, CircleShape)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}
