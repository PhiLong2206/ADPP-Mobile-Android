package com.adpp.mobile.feature.debate.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.DebateModeCard
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun DebateModeSelectionScreen(
    onNavigateToAISetup: () -> Unit,
    onNavigateTo1vs1: () -> Unit
) {
    var selectedMode by remember { mutableStateOf("ai_sparring") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Chọn chế độ tranh biện",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Lựa chọn hình thức rèn luyện phù hợp với mục tiêu học tập và thời gian của bạn hôm nay.",
            fontSize = 13.sp,
            color = TextSecondary,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            // Lựa chọn 1: Luyện tập với AI
            DebateModeCard(
                title = "1. Luyện tập với AI",
                description = "Tranh biện trực tiếp với trợ lý AI thông minh theo chủ đề tùy chọn, nhận phản hồi lập luận và rubric chấm điểm tức thì.",
                icon = Icons.Filled.Psychology,
                badgeText = "Khuyên dùng",
                durationText = "⏱ Thời lượng ước tính: 5 – 10 phút",
                isSelected = selectedMode == "ai_sparring",
                onClick = { selectedMode = "ai_sparring" }
            )

            // Lựa chọn 2: Tranh biện trực tiếp 1vs1
            DebateModeCard(
                title = "2. Tranh biện trực tiếp 1vs1",
                description = "Ghép cặp trực tuyến ngẫu nhiên hoặc tạo phòng đấu kín với bạn bè theo định dạng đối kháng trực tiếp 1 đấu 1.",
                icon = Icons.Filled.Person,
                badgeText = "Trực tuyến 1v1",
                durationText = "⏱ Thời lượng ước tính: 15 phút",
                isSelected = selectedMode == "debate_1vs1",
                onClick = { selectedMode = "debate_1vs1" }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        ADPPPrimaryButton(
            text = if (selectedMode == "ai_sparring") "Thiết lập phòng luyện tập với AI" else "Vào danh sách phòng đấu 1vs1",
            onClick = {
                if (selectedMode == "ai_sparring") onNavigateToAISetup()
                else onNavigateTo1vs1()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
