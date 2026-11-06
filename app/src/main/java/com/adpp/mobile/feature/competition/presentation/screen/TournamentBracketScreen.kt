package com.adpp.mobile.feature.competition.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.ChipShape
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.competition.presentation.viewmodel.CompetitionViewModel

@Composable
fun TournamentBracketScreen(
    viewModel: CompetitionViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedStage by remember { mutableStateOf("Tất cả") }

    val stages = listOf("Tất cả", "Tứ kết", "Bán kết", "Chung kết")

    val filteredMatches = when (selectedStage) {
        "Tứ kết" -> uiState.bracketMatches.filter { it.roundName.contains("Tứ kết") }
        "Bán kết" -> uiState.bracketMatches.filter { it.roundName.contains("Bán kết") }
        "Chung kết" -> uiState.bracketMatches.filter { it.roundName.contains("Chung kết") }
        else -> uiState.bracketMatches
    }

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
                    text = "Cây nhánh đấu loại",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Text(
                    text = "Sơ đồ nhánh đấu loại trực tiếp (Knockout Tree)",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Stage Filter Tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            stages.forEach { stage ->
                val isSelected = selectedStage == stage
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(ChipShape)
                        .background(if (isSelected) PrimaryEmerald else LightSurface)
                        .border(
                            width = 1.dp,
                            color = if (isSelected) PrimaryEmerald else LightBorder,
                            shape = ChipShape
                        )
                        .clickable { selectedStage = stage }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stage,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) LightSurface else TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bracket Matches List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredMatches) { match ->
                ADPPCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = match.roundName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryEmerald
                            )
                            ADPPStatusBadge(
                                text = "Đã kết thúc",
                                variant = BadgeVariant.NEUTRAL,
                                showDot = false
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Team 1
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (match.winner == match.team1) {
                                    Icon(
                                        imageVector = Icons.Filled.EmojiEvents,
                                        contentDescription = null,
                                        tint = AccentAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                Text(
                                    text = match.team1,
                                    fontSize = 14.sp,
                                    fontWeight = if (match.winner == match.team1) FontWeight.Bold else FontWeight.Normal,
                                    color = if (match.winner == match.team1) TextHeading else TextSecondary
                                )
                            }
                            Text(
                                text = match.team1Score.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (match.winner == match.team1) PrimaryEmerald else TextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = LightBorder, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(8.dp))

                        // Team 2
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (match.winner == match.team2) {
                                    Icon(
                                        imageVector = Icons.Filled.EmojiEvents,
                                        contentDescription = null,
                                        tint = AccentAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                Text(
                                    text = match.team2,
                                    fontSize = 14.sp,
                                    fontWeight = if (match.winner == match.team2) FontWeight.Bold else FontWeight.Normal,
                                    color = if (match.winner == match.team2) TextHeading else TextSecondary
                                )
                            }
                            Text(
                                text = match.team2Score.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (match.winner == match.team2) PrimaryEmerald else TextSecondary
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
