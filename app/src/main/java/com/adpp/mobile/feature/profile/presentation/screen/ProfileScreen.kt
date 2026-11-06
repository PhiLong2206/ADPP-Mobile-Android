package com.adpp.mobile.feature.profile.presentation.screen

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPAvatar
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPStatCard
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.components.ConfirmationDialog
import com.adpp.mobile.core.designsystem.theme.AccentRose
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateToEditProfile: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onSignOutToLogin: () -> Unit
) {
    val profile by viewModel.profileState.collectAsState()
    val scrollState = rememberScrollState()
    var showSignOutDialog by remember { mutableStateOf(false) }

    if (showSignOutDialog) {
        ConfirmationDialog(
            title = "Đăng xuất khỏi ADPP?",
            message = "Bạn có thể đăng nhập lại bất kỳ lúc nào bằng tài khoản học viên hoặc dùng chế độ dùng thử (Demo).",
            confirmText = "Đăng xuất",
            dismissText = "Hủy",
            isDestructive = true,
            icon = Icons.AutoMirrored.Filled.Logout,
            onConfirm = onSignOutToLogin,
            onDismiss = { showSignOutDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Hồ sơ cá nhân",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Profile Bio Card
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ADPPAvatar(
                        initials = "MA",
                        size = 64.dp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = profile.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextHeading
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = profile.institution,
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        ADPPStatusBadge(
                            text = "${profile.title} • ${profile.academicLevel}",
                            variant = BadgeVariant.PRIMARY,
                            showDot = true
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = profile.bio,
                    fontSize = 13.sp,
                    color = TextBody,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Practice Statistics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ADPPStatCard(
                title = "Tổng số trận",
                value = "${profile.totalMatches}",
                subtitle = "Luyện AI (52) • 1vs1 (32)",
                icon = Icons.Filled.Analytics,
                modifier = Modifier.weight(1f)
            )
            ADPPStatCard(
                title = "Tỷ lệ thắng 1vs1",
                value = "${profile.winRate}%",
                subtitle = "64 Thắng / 20 Thua",
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                accentColor = AccentSky,
                iconTint = AccentSky,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Navigation Menu Card
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileMenuItem(
                    icon = Icons.Filled.Edit,
                    title = "Chỉnh sửa thông tin cá nhân",
                    subtitle = "Cập nhật trường học, tiểu sử và sở thích luyện tập",
                    onClick = onNavigateToEditProfile
                )
                HorizontalDivider(color = LightBorder, modifier = Modifier.padding(vertical = 10.dp))
                ProfileMenuItem(
                    icon = Icons.Filled.History,
                    title = "Lịch sử các trận tranh biện",
                    subtitle = "Xem lại biên bản tranh luận AI & 1vs1 và đánh giá",
                    onClick = onNavigateToHistory
                )
                HorizontalDivider(color = LightBorder, modifier = Modifier.padding(vertical = 10.dp))
                ProfileMenuItem(
                    icon = Icons.Filled.Settings,
                    title = "Cài đặt ứng dụng",
                    subtitle = "Âm thanh, giọng nói AI, thông báo và giao diện",
                    onClick = onNavigateToSettings
                )
                HorizontalDivider(color = LightBorder, modifier = Modifier.padding(vertical = 10.dp))
                ProfileMenuItem(
                    icon = Icons.AutoMirrored.Filled.Logout,
                    title = "Đăng xuất",
                    subtitle = "Kết thúc phiên làm việc hiện tại",
                    tint = AccentRose,
                    onClick = { showSignOutDialog = true }
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    tint: androidx.compose.ui.graphics.Color = PrimaryEmerald,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(tint.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextSecondary
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = TextMuted,
            modifier = Modifier.size(18.dp)
        )
    }
}
