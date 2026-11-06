package com.adpp.mobile.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.AccentAmberLight
import com.adpp.mobile.core.designsystem.theme.AccentRose
import com.adpp.mobile.core.designsystem.theme.AccentRoseLight
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.AccentSkyLight
import com.adpp.mobile.core.designsystem.theme.ChipShape
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldDark
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight

enum class BadgeVariant {
    SUCCESS,
    WARNING,
    ERROR,
    INFO,
    PRIMARY,
    NEUTRAL
}

@Composable
fun ADPPStatusBadge(
    text: String,
    modifier: Modifier = Modifier,
    variant: BadgeVariant = BadgeVariant.PRIMARY,
    showDot: Boolean = true
) {
    val (bgColor, textColor, dotColor) = when (variant) {
        BadgeVariant.SUCCESS -> Triple(PrimaryEmeraldLight, PrimaryEmeraldDark, PrimaryEmeraldDark)
        BadgeVariant.WARNING -> Triple(AccentAmberLight, Color(0xFFB45309), Color(0xFFB45309))
        BadgeVariant.ERROR -> Triple(AccentRoseLight, AccentRose, AccentRose)
        BadgeVariant.INFO -> Triple(AccentSkyLight, AccentSky, AccentSky)
        BadgeVariant.PRIMARY -> Triple(PrimaryEmeraldLight, PrimaryEmerald, PrimaryEmerald)
        BadgeVariant.NEUTRAL -> Triple(Color(0xFFF1F5F9), Color(0xFF475569), Color(0xFF64748B))
    }

    Box(
        modifier = modifier
            .clip(ChipShape)
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showDot) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(dotColor)
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
            Text(
                text = text,
                color = textColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
