package com.adpp.mobile.feature.leaderboard.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPAvatar
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.LightSurfaceVariant
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.competition.presentation.viewmodel.CompetitionViewModel

@Composable
fun LeaderboardScreen(
    viewModel: CompetitionViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val topThree = uiState.topDebaters.take(3)
    val remainingDebaters = uiState.topDebaters.drop(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Quay lại",
                    tint = TextHeading
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Bảng xếp hạng toàn quốc",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Text(
                    text = "Bảng xếp hạng ELO chuẩn học thuật ADPP",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Top 3 Podium
        if (topThree.size >= 3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                // Hạng 2 (Bạc)
                PodiumColumn(
                    rank = 2,
                    name = topThree[1].name,
                    elo = topThree[1].elo,
                    badgeColor = Color(0xFF94A3B8),
                    podiumHeight = 85.dp
                )

                // Hạng 1 (Vàng)
                PodiumColumn(
                    rank = 1,
                    name = topThree[0].name,
                    elo = topThree[0].elo,
                    badgeColor = AccentAmber,
                    podiumHeight = 115.dp,
                    isFirst = true
                )

                // Hạng 3 (Đồng)
                PodiumColumn(
                    rank = 3,
                    name = topThree[2].name,
                    elo = topThree[2].elo,
                    badgeColor = Color(0xFFD97706),
                    podiumHeight = 65.dp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Remaining Debaters List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(remainingDebaters) { debater ->
                val isCurrentUser = debater.name.contains("Bạn")
                ADPPCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (isCurrentUser) Modifier.border(
                                1.5.dp,
                                PrimaryEmerald,
                                RoundedCornerShape(18.dp)
                            )
                            else Modifier
                        )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Rank Number
                        Text(
                            text = "#${debater.rank}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isCurrentUser) PrimaryEmerald else TextMuted,
                            modifier = Modifier.width(36.dp)
                        )

                        ADPPAvatar(
                            name = debater.name.replace(" (Bạn)", ""),
                            size = 40.dp
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = debater.name,
                                fontSize = 14.sp,
                                fontWeight = if (isCurrentUser) FontWeight.Bold else FontWeight.SemiBold,
                                color = TextHeading
                            )
                            Text(
                                text = "${debater.institution} • ${debater.winRate}% thắng (${debater.matchesPlayed} trận)",
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }

                        // ELO
                        Text(
                            text = "${debater.elo}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isCurrentUser) PrimaryEmerald else AccentSky
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun PodiumColumn(
    rank: Int,
    name: String,
    elo: Int,
    badgeColor: Color,
    podiumHeight: Dp,
    isFirst: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        if (isFirst) {
            Icon(
                imageVector = Icons.Filled.EmojiEvents,
                contentDescription = null,
                tint = AccentAmber,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        ADPPAvatar(
            name = name,
            size = if (isFirst) 54.dp else 44.dp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = name,
            fontSize = if (isFirst) 13.sp else 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading,
            maxLines = 1
        )

        Text(
            text = "$elo ELO",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryEmerald
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Podium Block
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(podiumHeight)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(if (isFirst) PrimaryEmeraldLight else LightSurfaceVariant)
                .border(
                    width = 1.dp,
                    color = if (isFirst) PrimaryEmerald else LightBorder,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = rank.toString(),
                fontSize = if (isFirst) 28.sp else 22.sp,
                fontWeight = FontWeight.Bold,
                color = badgeColor
            )
        }
    }
}
