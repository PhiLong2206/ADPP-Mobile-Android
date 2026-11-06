package com.adpp.mobile.core.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.AccentSkyLight
import com.adpp.mobile.core.designsystem.theme.CardShape
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun DebateTopicCard(
    title: String,
    category: String,
    difficulty: String,
    participantCount: Int,
    modifier: Modifier = Modifier,
    summary: String? = null,
    onClick: () -> Unit = {}
) {
    ADPPCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ADPPStatusBadge(
                    text = category,
                    variant = BadgeVariant.PRIMARY,
                    showDot = false
                )
                ADPPStatusBadge(
                    text = difficulty,
                    variant = when (difficulty.lowercase()) {
                        "dễ", "cơ bản", "beginner" -> BadgeVariant.SUCCESS
                        "trung bình", "intermediate" -> BadgeVariant.WARNING
                        else -> BadgeVariant.ERROR
                    },
                    showDot = true
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading,
                lineHeight = 23.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (!summary.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = summary,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 19.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$participantCount lượt tranh biện",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Chọn chủ đề",
                    tint = PrimaryEmerald,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun DebateModeCard(
    title: String,
    description: String,
    icon: ImageVector,
    badgeText: String,
    isSelected: Boolean = false,
    durationText: String? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) PrimaryEmerald else LightBorder,
        label = "modeBorder"
    )

    ADPPCard(
        modifier = modifier.fillMaxWidth(),
        borderColor = borderColor,
        borderWidth = if (isSelected) 2.dp else 1.dp,
        elevation = if (isSelected) 2.dp else 1.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) PrimaryEmeraldLight else LightBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) PrimaryEmerald else TextSecondary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                    ADPPStatusBadge(
                        text = badgeText,
                        variant = if (isSelected) BadgeVariant.PRIMARY else BadgeVariant.NEUTRAL,
                        showDot = false
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
                if (!durationText.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = durationText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryEmerald
                    )
                }
            }
        }
    }
}

@Composable
fun ScoreCard(
    criterion: String,
    score: Float,
    maxScore: Float = 100f,
    feedback: String,
    modifier: Modifier = Modifier
) {
    val progress = (score / maxScore).coerceIn(0f, 1f)
    val scoreColor = when {
        progress >= 0.8f -> PrimaryEmerald
        progress >= 0.6f -> AccentSky
        else -> Color(0xFFF59E0B)
    }

    ADPPCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = criterion,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextHeading
                )
                Text(
                    text = "${score.toInt()} / ${maxScore.toInt()}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = scoreColor
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = scoreColor,
                trackColor = LightBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedback,
                fontSize = 13.sp,
                color = TextBody,
                lineHeight = 18.sp
            )
        }
    }
}
