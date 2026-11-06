package com.adpp.mobile.feature.competition.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPOutlinedButton
import com.adpp.mobile.core.designsystem.components.CompetitionCard
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.competition.presentation.viewmodel.CompetitionViewModel

@Composable
fun CompetitionListScreen(
    viewModel: CompetitionViewModel,
    onCompetitionSelected: (String) -> Unit,
    onNavigateToLeaderboard: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Giải đấu & Cúp",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Text(
                    text = "Các giải đấu tranh biện học thuật cấp quốc gia",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            ADPPOutlinedButton(
                text = "Xếp hạng",
                leadingIcon = Icons.Filled.Leaderboard,
                borderColor = AccentAmber,
                textColor = AccentAmber,
                onClick = onNavigateToLeaderboard
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Competition Cards List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(uiState.competitions) { comp ->
                CompetitionCard(
                    title = comp.title,
                    format = comp.format,
                    prizePool = comp.prizePool,
                    dateRange = comp.dateRange,
                    registeredTeams = comp.registeredTeams,
                    maxTeams = comp.maxTeams,
                    status = comp.status,
                    onClick = { onCompetitionSelected(comp.id) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
