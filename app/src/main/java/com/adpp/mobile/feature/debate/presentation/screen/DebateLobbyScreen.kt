package com.adpp.mobile.feature.debate.presentation.screen

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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.adpp.mobile.core.designsystem.components.ADPPAvatar
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPSecondaryButton
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun DebateLobbyScreen(
    onNavigateBack: () -> Unit,
    onStartLiveMatch: () -> Unit
) {
    var isUserReady by remember { mutableStateOf(true) }
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
            horizontalArrangement = Arrangement.SpaceBetween,
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
            Text(
                text = "Phòng chờ thi đấu 1vs1",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
            IconButton(
                onClick = {},
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ContentCopy,
                    contentDescription = "Sao chép liên kết",
                    tint = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card Thông tin phòng
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "MÃ PHÒNG #OXF-402",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentSky
                    )
                    ADPPStatusBadge(
                        text = "Cả hai đã sẵn sàng",
                        variant = BadgeVariant.PRIMARY,
                        showDot = true
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Nhà nước nên áp dụng thuế carbon bắt buộc đối với các ngành công nghiệp thâm dụng năng lượng.",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextHeading,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Thể thức: 1v1 Oxford • 2 phút/lượt",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "12 khán giả",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Danh sách đấu thủ
        Text(
            text = "Hai đấu thủ tranh luận (1v1)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Phe Ủng hộ (User)
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ADPPAvatar(initials = "MA", size = 46.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Nguyễn Minh Anh (Bạn)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                    Text(
                        text = "Phe Ủng hộ • Trình độ Nâng cao",
                        fontSize = 12.sp,
                        color = PrimaryEmerald,
                        fontWeight = FontWeight.Medium
                    )
                }
                ADPPStatusBadge(
                    text = if (isUserReady) "Sẵn sàng" else "Chưa sẵn sàng",
                    variant = if (isUserReady) BadgeVariant.PRIMARY else BadgeVariant.NEUTRAL,
                    showDot = true
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Phe Phản đối (Opponent)
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ADPPAvatar(initials = "BL", size = 46.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Trần Bảo Long",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                    Text(
                        text = "Phe Phản đối • Kỳ cựu (95 trận)",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
                ADPPStatusBadge(
                    text = "Sẵn sàng",
                    variant = BadgeVariant.PRIMARY,
                    showDot = true
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Quy tắc phòng đấu
        ADPPCard(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = LightSurface
        ) {
            Column {
                Text(
                    text = "Quy chuẩn phòng đấu học thuật",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "• Hội đồng Trọng tài AI tự động chấm điểm theo thang rubric 100 điểm.\n" +
                            "• Micro sẽ tự động chuyển lượt theo đúng thời lượng quy định.\n" +
                            "• Nghiêm cấm mọi hành vi công kích cá nhân (Ad Hominem).",
                    fontSize = 12.sp,
                    color = TextBody,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Action Buttons
        ADPPPrimaryButton(
            text = "Bắt đầu trận đấu 1vs1",
            icon = Icons.Filled.PlayArrow,
            enabled = isUserReady,
            onClick = onStartLiveMatch
        )

        Spacer(modifier = Modifier.height(10.dp))

        ADPPSecondaryButton(
            text = if (isUserReady) "Hủy trạng thái sẵn sàng" else "Tôi đã sẵn sàng",
            onClick = { isUserReady = !isUserReady }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
