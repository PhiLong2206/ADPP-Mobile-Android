package com.adpp.mobile.feature.debate.presentation.screen

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun CreateDebateRoomScreen(
    onNavigateBack: () -> Unit,
    onRoomCreated: (String) -> Unit
) {
    var roomTitle by remember { mutableStateOf("Phòng luyện tập phản biện 1vs1") }
    var motion by remember { mutableStateOf("Nên áp dụng thuế lũy tiến đặc biệt đối với các công ty khai thác dữ liệu số lớn.") }
    var selectedFormat by remember { mutableStateOf("1v1 Oxford") }
    var speechDuration by remember { mutableStateOf("2 phút") }
    var isPrivate by remember { mutableStateOf(false) }
    var passcode by remember { mutableStateOf("") }

    val formats = listOf("1v1 Oxford", "1v1 Cổ điển", "1v1 Tự do")
    val durations = listOf("1 phút", "2 phút", "3 phút")
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .imePadding()
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
                text = "Tạo phòng tranh biện 1vs1",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tên phòng
        ADPPTextField(
            value = roomTitle,
            onValueChange = { roomTitle = it },
            label = "Tên phòng luyện tập",
            placeholder = "VD: Luyện phản biện 1vs1..."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Định dạng tranh biện
        Text(
            text = "Thể thức tranh biện 1vs1",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextHeading
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            formats.forEach { fmt ->
                val isSelected = selectedFormat == fmt
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(ButtonShape)
                        .background(if (isSelected) PrimaryEmerald else LightSurface)
                        .border(1.dp, if (isSelected) PrimaryEmerald else LightBorder, ButtonShape)
                        .clickable { selectedFormat = fmt }
                        .padding(vertical = 12.dp, horizontal = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = fmt,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) LightSurface else TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Kiến nghị tranh biện
        Text(
            text = "Kiến nghị tranh biện (Motion)",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextHeading
        )
        Spacer(modifier = Modifier.height(8.dp))
        ADPPTextField(
            value = motion,
            onValueChange = { motion = it },
            label = "Nội dung kiến nghị tranh luận",
            singleLine = false,
            maxLines = 3,
            placeholder = "Nhập chủ đề kiến nghị tranh luận 1v1..."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Thời gian phát biểu mỗi lượt
        Text(
            text = "Thời gian giới hạn mỗi lượt nói",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextHeading
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            durations.forEach { dur ->
                val isSelected = speechDuration == dur
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(ButtonShape)
                        .background(if (isSelected) PrimaryEmerald else LightSurface)
                        .border(1.dp, if (isSelected) PrimaryEmerald else LightBorder, ButtonShape)
                        .clickable { speechDuration = dur }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dur,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) LightSurface else TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Cài đặt phòng riêng tư
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(ButtonShape)
                .background(LightSurface)
                .border(1.dp, LightBorder, ButtonShape)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Phòng kín (Yêu cầu mật khẩu)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextHeading
                )
                Text(
                    text = "Chỉ người có mã PIN mới có thể tham gia",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
            Switch(
                checked = isPrivate,
                onCheckedChange = { isPrivate = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = LightSurface,
                    checkedTrackColor = PrimaryEmerald,
                    uncheckedThumbColor = LightSurface,
                    uncheckedTrackColor = LightBorder
                )
            )
        }

        if (isPrivate) {
            Spacer(modifier = Modifier.height(14.dp))
            ADPPTextField(
                value = passcode,
                onValueChange = { passcode = it },
                label = "Mật khẩu phòng (PIN 4 số)",
                placeholder = "Nhập 4 số PIN..."
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        ADPPPrimaryButton(
            text = "Khởi tạo phòng đấu 1vs1",
            onClick = {
                onRoomCreated("room-custom-101")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
