package com.adpp.mobile.feature.debate.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Psychology
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPTextField
import com.adpp.mobile.core.designsystem.theme.ButtonShape
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.debate.presentation.viewmodel.DebateViewModel

@Composable
fun AIDebateSetupScreen(
    viewModel: DebateViewModel,
    onNavigateBack: () -> Unit,
    onStartDebate: () -> Unit
) {
    val setupState by viewModel.setupState.collectAsState()
    val scrollState = rememberScrollState()

    val difficulties = listOf("Cơ bản", "Trung cấp", "Nâng cao", "Thích ứng thông minh")
    val sides = listOf("Phe Ủng hộ", "Phe Phản đối", "Ngẫu nhiên")
    val speechDurations = listOf(60 to "1 phút", 120 to "2 phút", 180 to "3 phút")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
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
            Text(
                text = "Thiết lập phòng luyện tập AI",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Nhập chủ đề / Nghị quyết
        Text(
            text = "Chủ đề / Kiến nghị tranh biện",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextHeading
        )
        Spacer(modifier = Modifier.height(8.dp))
        ADPPTextField(
            value = setupState.motion,
            onValueChange = { viewModel.updateSetupMotion(it) },
            label = "Nội dung kiến nghị tranh luận",
            singleLine = false,
            maxLines = 4,
            placeholder = "Nhập chủ đề kiến nghị tranh luận..."
        )

        Spacer(modifier = Modifier.height(22.dp))

        // Chọn phe của bạn
        Text(
            text = "Lựa chọn lập trường (Phe của bạn)",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextHeading
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sides.forEach { side ->
                val isSelected = setupState.selectedSide == side
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(ButtonShape)
                        .background(if (isSelected) PrimaryEmerald else LightSurface)
                        .clickable { viewModel.updateSetupSide(side) }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = side,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Độ khó đối thủ AI
        Text(
            text = "Mức độ thử thách của AI",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextHeading
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            difficulties.forEach { diff ->
                val isSelected = setupState.aiDifficulty == diff
                ADPPCard(
                    modifier = Modifier.fillMaxWidth(),
                    borderColor = if (isSelected) PrimaryEmerald else LightBorder,
                    borderWidth = if (isSelected) 2.dp else 1.dp,
                    onClick = { viewModel.updateSetupDifficulty(diff) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) PrimaryEmeraldLight else LightBackground),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Psychology,
                                    contentDescription = null,
                                    tint = if (isSelected) PrimaryEmerald else TextSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = diff,
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = TextHeading
                            )
                        }
                        if (isSelected) {
                            Text(
                                text = "Đang chọn",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryEmerald
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Thời gian mỗi lượt nói
        Text(
            text = "Thời gian giới hạn mỗi lượt",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextHeading
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            speechDurations.forEach { (seconds, label) ->
                val isSelected = setupState.speechTimeSeconds == seconds
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(ButtonShape)
                        .background(if (isSelected) PrimaryEmerald else LightSurface)
                        .clickable { viewModel.updateSetupTime(seconds) }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        ADPPPrimaryButton(
            text = "Bắt đầu vào phòng tranh biện",
            leadingIcon = Icons.Filled.FlashOn,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
            onClick = onStartDebate
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
