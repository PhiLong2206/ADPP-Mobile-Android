package com.adpp.mobile.feature.competition.presentation.screen

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPSecondaryButton
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun CompetitionDetailsScreen(
    competitionId: String,
    onNavigateBack: () -> Unit,
    onViewBracket: () -> Unit
) {
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
                text = "Chi tiết giải đấu",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Tournament Card
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ADPPStatusBadge(
                        text = "Nghị viện Anh (2v2)",
                        variant = BadgeVariant.NEUTRAL,
                        showDot = false
                    )
                    ADPPStatusBadge(
                        text = "Đang diễn ra",
                        variant = BadgeVariant.PRIMARY,
                        showDot = true
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Giải Vô địch Tranh biện Sinh viên Toàn quốc 2026",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading,
                    lineHeight = 26.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.MilitaryTech,
                        contentDescription = null,
                        tint = AccentAmber,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Tổng giải thưởng: 50.000.000 đ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentAmber
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = LightBorder, thickness = 1.dp)
                Spacer(modifier = Modifier.height(14.dp))

                // Stats Grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "15/11 - 20/11/2026",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Group,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "64/64 Đội tham gia",
                            fontSize = 12.sp,
                            color = PrimaryEmerald,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Giới thiệu giải đấu
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "Giới thiệu giải đấu",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Giải Vô địch Tranh biện Sinh viên Toàn quốc 2026 là giải đấu tranh biện thường niên lớn nhất cả nước, quy tụ các câu lạc bộ tranh biện xuất sắc từ hơn 40 trường đại học. Các trận đấu áp dụng luật Nghị viện Anh (British Parliamentary) tiêu chuẩn quốc tế và được giám sát bởi Hội đồng Trọng tài uy tín kết hợp công nghệ AI ADPP Adjudicator.",
                    fontSize = 13.sp,
                    color = TextBody,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Lộ trình thi đấu
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "Lộ trình & Cấu trúc thi đấu",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Vòng bảng: 4 lượt đấu tính điểm ELO theo hệ số Elo-Swiss",
                        fontSize = 12.sp,
                        color = TextBody
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.AccountTree,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Vòng Knockout: Tứ kết, Bán kết và Trận Chung kết Tổng",
                        fontSize = 12.sp,
                        color = TextBody
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Buttons
        ADPPPrimaryButton(
            text = "Xem cây nhánh đấu loại (Bracket)",
            icon = Icons.Filled.AccountTree,
            onClick = onViewBracket
        )

        Spacer(modifier = Modifier.height(12.dp))

        ADPPSecondaryButton(
            text = "Đã đủ số lượng đội tham gia",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
