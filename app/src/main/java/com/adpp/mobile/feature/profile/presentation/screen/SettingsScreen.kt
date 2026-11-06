package com.adpp.mobile.feature.profile.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun SettingsScreen(
    viewModel: ProfileViewModel,
    onNavigateBack: () -> Unit
) {
    val profile by viewModel.profileState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .verticalScroll(scrollState)
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
            Text(
                text = "Cài đặt ứng dụng",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Giao diện
        Text(
            text = "Giao diện & Hiển thị",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(PrimaryEmerald.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LightMode,
                            contentDescription = null,
                            tint = PrimaryEmerald,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Giao diện Sáng (EdTech V3)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextHeading
                        )
                        Text(
                            text = "Tối ưu hóa khả năng đọc & tập trung",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }
                ADPPStatusBadge(
                    text = "Đang kích hoạt",
                    variant = BadgeVariant.PRIMARY,
                    showDot = false
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Âm thanh & Giọng nói
        Text(
            text = "Giọng nói & Hiệu ứng âm thanh",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SettingSwitchRow(
                    title = "Tổng hợp giọng nói AI (Neural TTS)",
                    subtitle = "Phát âm thanh bài phát biểu lập luận của AI tự động",
                    isChecked = profile.voiceSpeechEnabled,
                    onCheckedChange = { viewModel.toggleVoiceSpeech() }
                )
                HorizontalDivider(color = LightBorder, modifier = Modifier.padding(vertical = 10.dp))
                SettingSwitchRow(
                    title = "Âm thanh & Chuông báo thời gian",
                    subtitle = "Rung chuông khi hết thời gian phát biểu hoặc có tín hiệu POI",
                    isChecked = profile.soundEffectsEnabled,
                    onCheckedChange = { viewModel.toggleSoundEffects() }
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Thông báo
        Text(
            text = "Thông báo & Lời mời",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            SettingSwitchRow(
                title = "Lời mời thi đấu 1vs1",
                subtitle = "Nhận thông báo khi đối thủ mời tham gia phòng tranh biện 1v1",
                isChecked = profile.notificationsEnabled,
                onCheckedChange = { viewModel.toggleNotifications() }
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Thông tin nền tảng
        Text(
            text = "Thông tin nền tảng ADPP",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Phiên bản ứng dụng",
                        fontSize = 13.sp,
                        color = TextHeading
                    )
                    Text(
                        text = "ADPP Mobile v3.0 (EdTech Release)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryEmerald
                    )
                }
                HorizontalDivider(color = LightBorder, modifier = Modifier.padding(vertical = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Chế độ hoạt động",
                        fontSize = 13.sp,
                        color = TextHeading
                    )
                    Text(
                        text = "Bản thử nghiệm giao diện (UI Demo)",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
                HorizontalDivider(color = LightBorder, modifier = Modifier.padding(vertical = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tổ chức phát triển",
                        fontSize = 13.sp,
                        color = TextHeading
                    )
                    Text(
                        text = "Đội ngũ Khóa Luận Tốt Nghiệp ADPP",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
private fun SettingSwitchRow(
    title: String,
    subtitle: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextHeading
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 16.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = LightSurface,
                checkedTrackColor = PrimaryEmerald,
                uncheckedThumbColor = LightSurface,
                uncheckedTrackColor = LightBorder
            )
        )
    }
}
