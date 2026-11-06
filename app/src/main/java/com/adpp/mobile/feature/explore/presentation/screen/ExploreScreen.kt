package com.adpp.mobile.feature.explore.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPSearchBar
import com.adpp.mobile.core.designsystem.components.DebateTopicCard
import com.adpp.mobile.core.designsystem.components.EmptyState
import com.adpp.mobile.core.designsystem.theme.ChipShape
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.explore.presentation.viewmodel.ExploreViewModel

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel,
    onTopicSelected: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val filteredTopics = viewModel.getFilteredTopics()
    val categoryScrollState = rememberScrollState()
    val difficultyScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Tiêu đề màn hình
        Text(
            text = "Khám phá chủ đề",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Kho kiến nghị tranh biện đa dạng chuẩn Nghị viện & Oxford học thuật.",
            fontSize = 13.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Thanh tìm kiếm lớn, dễ sử dụng
        ADPPSearchBar(
            query = uiState.searchQuery,
            onQueryChange = { viewModel.updateSearchQuery(it) },
            placeholder = "Tìm kiếm chủ đề, từ khóa kiến nghị..."
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Bộ lọc danh mục dạng chip cuộn ngang
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(categoryScrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.categories.forEach { category ->
                val isSelected = uiState.selectedCategory == category
                Box(
                    modifier = Modifier
                        .clip(ChipShape)
                        .background(if (isSelected) PrimaryEmerald else LightSurface)
                        .border(
                            width = 1.dp,
                            color = if (isSelected) PrimaryEmerald else LightBorder,
                            shape = ChipShape
                        )
                        .clickable { viewModel.selectCategory(category) }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) LightSurface else TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Bộ lọc mức độ khó dạng chip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(difficultyScrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.difficulties.forEach { diff ->
                val isSelected = uiState.selectedDifficulty == diff
                Box(
                    modifier = Modifier
                        .clip(ChipShape)
                        .background(if (isSelected) PrimaryEmeraldLight else LightSurface)
                        .border(
                            width = 1.dp,
                            color = if (isSelected) PrimaryEmerald else LightBorder,
                            shape = ChipShape
                        )
                        .clickable { viewModel.selectDifficulty(diff) }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Mức độ: $diff",
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) PrimaryEmerald else TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Kết quả tìm kiếm
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${filteredTopics.size} chủ đề sẵn sàng luyện tập",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextMuted
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Danh sách chủ đề hoặc Trạng thái trống
        if (filteredTopics.isEmpty()) {
            EmptyState(
                title = "Không tìm thấy chủ đề phù hợp",
                message = "Hãy thử thay đổi từ khóa tìm kiếm hoặc chọn danh mục khác để khám phá các kiến nghị phong phú.",
                icon = Icons.Filled.SearchOff
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredTopics, key = { it.id }) { topic ->
                    DebateTopicCard(
                        title = topic.title,
                        category = topic.category,
                        difficulty = topic.difficulty,
                        participantCount = topic.participantCount,
                        summary = topic.summary,
                        onClick = { onTopicSelected(topic.id) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
