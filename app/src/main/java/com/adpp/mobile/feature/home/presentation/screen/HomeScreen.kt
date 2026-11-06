package com.adpp.mobile.feature.home.presentation.screen

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
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import com.adpp.mobile.core.designsystem.components.ADPPAvatar
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPSecondaryButton
import com.adpp.mobile.core.designsystem.components.ADPPStatCard
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.components.DebateTopicCard
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.AccentAmberLight
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.AccentSkyLight
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToDebateSetup: () -> Unit,
    onNavigateToExplore: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateTo1vs1: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // 1. Header: Lời chào, Chuỗi ngày & Avatar người dùng
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Xin chào, ${uiState.userName}!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Hôm nay bạn muốn luyện tập chủ đề gì?",
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Chuỗi ngày học tập
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(AccentAmberLight)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.LocalFireDepartment,
                            contentDescription = "Chuỗi ngày",
                            tint = AccentAmber,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${uiState.currentStreakDays} ngày",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentAmber
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                // Avatar người dùng
                Box(modifier = Modifier.clickable { onNavigateToProfile() }) {
                    ADPPAvatar(
                        name = uiState.userName,
                        size = 42.dp,
                        showBorder = true,
                        isOnline = true
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 2. Chế độ nổi bật: Luyện tập tranh biện với AI
        ADPPCard(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = LightSurface,
            borderColor = PrimaryEmerald.copy(alpha = 0.35f),
            elevation = 2.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ADPPStatusBadge(
                        text = "Trợ lý AI Nova",
                        variant = BadgeVariant.PRIMARY,
                        showDot = true
                    )
                    Text(
                        text = "Chấm điểm tự động",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Bắt đầu luyện tranh biện với AI",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Rèn giũa lập luận và tư duy phản biện tức thì cùng trợ lý AI được tối ưu theo quy chuẩn học thuật.",
                    fontSize = 13.sp,
                    color = TextBody,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                ADPPPrimaryButton(
                    text = "Vào phòng luyện tập AI ngay",
                    leadingIcon = Icons.Filled.FlashOn,
                    onClick = onNavigateToDebateSetup
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3. Chế độ đối kháng trực tiếp: Tranh biện 1vs1
        ADPPCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNavigateTo1vs1
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(AccentSkyLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = AccentSky,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Tranh biện trực tiếp 1vs1",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextHeading
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        ADPPStatusBadge(
                            text = "Trực tuyến",
                            variant = BadgeVariant.PRIMARY,
                            showDot = true
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Ghép cặp phòng thi đấu 1 đấu 1 hoặc tạo phòng riêng",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 4. Thống kê tiến độ học tập
        Text(
            text = "Tiến độ học tập",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ADPPStatCard(
                title = "Số trận tham gia",
                value = "${uiState.totalDebates}",
                subtitle = "+4 tuần này",
                icon = Icons.Filled.Analytics,
                modifier = Modifier.weight(1f)
            )
            ADPPStatCard(
                title = "Tỷ lệ thắng 1v1",
                value = "${uiState.winRatePercent}%",
                subtitle = "Top 8% nền tảng",
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                iconTint = AccentSky,
                iconBgColor = AccentSkyLight,
                modifier = Modifier.weight(1f)
            )
            ADPPStatCard(
                title = "Điểm đánh giá",
                value = "${uiState.averageScore}",
                subtitle = "Chuẩn xuất sắc",
                icon = Icons.Filled.Star,
                iconTint = AccentAmber,
                iconBgColor = AccentAmberLight,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 5. Chủ đề gợi ý hôm nay
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chủ đề gợi ý hôm nay",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
            Text(
                text = "Xem tất cả",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryEmerald,
                modifier = Modifier.clickable { onNavigateToExplore() }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            uiState.recommendedTopics.forEach { topic ->
                DebateTopicCard(
                    title = topic.title,
                    category = topic.category,
                    difficulty = topic.difficulty,
                    participantCount = topic.participantCount,
                    onClick = onNavigateToDebateSetup
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
