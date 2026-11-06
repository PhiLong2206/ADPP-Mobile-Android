package com.adpp.mobile.feature.debate.presentation.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPAvatar
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPSecondaryButton
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.components.ConfirmationDialog
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.AccentAmberLight
import com.adpp.mobile.core.designsystem.theme.AccentRose
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.AccentSkyLight
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun LiveDebateScreen(
    onNavigateBack: () -> Unit,
    onFinishDebateToEvaluation: () -> Unit
) {
    var showConcludeDialog by remember { mutableStateOf(false) }
    var poiRequested by remember { mutableStateOf(false) }
    var isMicMuted by remember { mutableStateOf(false) }
    val pulseScale = remember { Animatable(1f) }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        pulseScale.animateTo(
            targetValue = 1.15f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 700, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    if (showConcludeDialog) {
        ConfirmationDialog(
            title = "Kết thúc trận tranh biện 1vs1?",
            message = "Toàn bộ bài phát biểu và dữ liệu đối đáp của hai bên sẽ được chuyển đến Hội đồng Trọng tài AI để phân tích và chấm điểm chi tiết.",
            confirmText = "Kết thúc & Chấm điểm",
            dismissText = "Tiếp tục trận đấu",
            onConfirm = onFinishDebateToEvaluation,
            onDismiss = { showConcludeDialog = false }
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
        // Top Bar: Trực tiếp 1vs1 & Người xem
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = "Phòng đấu 1vs1 #OXF-402",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                    Text(
                        text = "Thể thức 1v1 Oxford • Lượt 2/4",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                ADPPStatusBadge(
                    text = "TRỰC TIẾP",
                    variant = BadgeVariant.ERROR,
                    showDot = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "48",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Motion Card
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "Chủ đề: Nhà nước nên áp dụng thuế carbon bắt buộc đối với các ngành công nghiệp thâm dụng năng lượng.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trọng tài AI: Đang ghi nhận luận điểm",
                        fontSize = 12.sp,
                        color = PrimaryEmerald,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Thời gian mỗi lượt: 2 phút",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 1vs1 Active Speaker Stage
        ADPPCard(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, PrimaryEmerald, RoundedCornerShape(18.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ADPPStatusBadge(
                        text = "ĐANG PHÁT BIỂU",
                        variant = BadgeVariant.PRIMARY,
                        showDot = true
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Timer,
                            contentDescription = null,
                            tint = AccentAmber,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "01:18",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentAmber
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(PrimaryEmeraldLight),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .scale(pulseScale.value)
                            .clip(CircleShape)
                            .background(PrimaryEmerald.copy(alpha = 0.25f))
                    )
                    ADPPAvatar(name = "Nguyễn Minh Anh", size = 60.dp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Nguyễn Minh Anh (Bạn)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Text(
                    text = "Phe Ủng hộ • Lượt phát biểu chính thức",
                    fontSize = 12.sp,
                    color = PrimaryEmerald,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Live Audio Wave / Status Pill
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(LightBackground)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = if (isMicMuted) Icons.Filled.MicOff else Icons.Filled.Mic,
                        contentDescription = null,
                        tint = if (isMicMuted) AccentRose else PrimaryEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isMicMuted) "Micro đã tắt" else "Micro đang thu âm • Tự động chuyển đổi văn bản",
                        fontSize = 12.sp,
                        color = TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Opponent Card (Đối thủ 1vs1)
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ADPPAvatar(name = "Trần Bảo Long", size = 44.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Trần Bảo Long",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextHeading
                    )
                    Text(
                        text = "Phe Phản đối • Đang chờ lượt phản biện",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
                ADPPStatusBadge(
                    text = "Chờ phản biện",
                    variant = BadgeVariant.NEUTRAL,
                    showDot = false
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // POI Control
        if (poiRequested) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(PrimaryEmeraldLight)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✓ Đã gửi tín hiệu xin ngắt lời (POI) đến đối thủ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryEmerald
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Action Buttons Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                ADPPSecondaryButton(
                    text = if (poiRequested) "Hủy POI" else "Xin ngắt lời (POI)",
                    icon = Icons.Filled.PanTool,
                    onClick = { poiRequested = !poiRequested }
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                ADPPSecondaryButton(
                    text = if (isMicMuted) "Bật Micro" else "Tắt Micro",
                    icon = if (isMicMuted) Icons.Filled.MicOff else Icons.Filled.Mic,
                    onClick = { isMicMuted = !isMicMuted }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // End Match Button
        ADPPPrimaryButton(
            text = "Kết thúc trận đấu & Chấm điểm",
            icon = Icons.Filled.CallEnd,
            onClick = { showConcludeDialog = true }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
