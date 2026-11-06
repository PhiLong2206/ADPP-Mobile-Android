package com.adpp.mobile.feature.profile.presentation.screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
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
import com.adpp.mobile.core.designsystem.components.ADPPAvatar
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPTextField
import com.adpp.mobile.core.designsystem.theme.ButtonShape
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateBack: () -> Unit
) {
    val profile by viewModel.profileState.collectAsState()
    var name by remember { mutableStateOf(profile.name) }
    var bio by remember { mutableStateOf(profile.bio) }
    var institution by remember { mutableStateOf(profile.institution) }
    var selectedFormat by remember { mutableStateOf(profile.preferredFormat) }

    val formats = listOf("1v1 Oxford", "1v1 Cổ điển", "1v1 Tự do")
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
                text = "Chỉnh sửa thông tin cá nhân",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Avatar Upload
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                ADPPAvatar(initials = "MA", size = 88.dp)
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(PrimaryEmerald)
                        .border(2.dp, LightSurface, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = "Đổi ảnh đại diện",
                        tint = LightSurface,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Name
        ADPPTextField(
            value = name,
            onValueChange = { name = it },
            label = "Họ và tên",
            placeholder = "Nhập họ và tên đầy đủ..."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Institution / Club
        ADPPTextField(
            value = institution,
            onValueChange = { institution = it },
            label = "Trường học / Câu lạc bộ",
            placeholder = "VD: ĐH Bách Khoa, CLB Tranh biện..."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Bio
        ADPPTextField(
            value = bio,
            onValueChange = { bio = it },
            label = "Tiểu sử / Giới thiệu bản thân",
            singleLine = false,
            maxLines = 3,
            placeholder = "Giới thiệu ngắn gọn phong cách tranh biện của bạn..."
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Preferred Format
        Text(
            text = "Thể thức luyện tập 1vs1 yêu thích",
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

        Spacer(modifier = Modifier.height(32.dp))

        ADPPPrimaryButton(
            text = "Lưu thay đổi hồ sơ",
            onClick = {
                viewModel.updateName(name)
                viewModel.updateBio(bio)
                viewModel.updateInstitution(institution)
                viewModel.updatePreferredFormat(selectedFormat)
                onNavigateBack()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
