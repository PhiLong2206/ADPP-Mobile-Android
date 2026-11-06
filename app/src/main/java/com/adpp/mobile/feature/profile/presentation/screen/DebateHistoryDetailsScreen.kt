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
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun DebateHistoryDetailsScreen(
    matchId: String,
    onNavigateBack: () -> Unit,
    onReSparTopic: () -> Unit
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
                text = "Chi tiết trận đấu lưu trữ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Outcome Header Card
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ADPPStatusBadge(
                        text = "Luyện tập với AI • Oxford 1v1",
                        variant = BadgeVariant.NEUTRAL,
                        showDot = false
                    )
                    ADPPStatusBadge(
                        text = "CHIẾN THẮNG (92/100)",
                        variant = BadgeVariant.PRIMARY,
                        showDot = true
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Trí tuệ nhân tạo (AI) nên bị giới hạn trong việc sáng tạo các tác phẩm nghệ thuật có bản quyền.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Vai trò: Phe Ủng hộ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryEmerald
                    )
                    Text(
                        text = "Đối thủ: Trợ lý AI Nova",
                        fontSize = 12.sp,
                        color = AccentSky
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Adjudicator Summary
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Assessment,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Nhận định của Hội đồng Trọng tài",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Phe Ủng hộ đã thiết lập gánh nặng chứng minh rất rõ ràng ngay từ phát biểu mở đầu. Việc phân biệt giữa việc con người học hỏi và thuật toán trích xuất dữ liệu hàng loạt nhằm mục đích thương mại đã hoàn toàn bẻ gãy luận điểm tương đồng của Phe Phản đối.",
                    fontSize = 13.sp,
                    color = TextBody,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Speech Transcripts Excerpt
        Text(
            text = "Trích lục các lượt tranh luận chính",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Round 1
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Lượt 1: Phe Ủng hộ (Bạn)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryEmerald
                    )
                    Text(text = "02:00", fontSize = 11.sp, color = TextMuted)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "\"Nếu sản phẩm AI được công nhận bản quyền bình đẳng với tác phẩm do con người sáng tạo, động lực đầu tư công sức của cộng đồng nghệ sĩ độc lập sẽ bị triệt tiêu hoàn toàn...\"",
                    fontSize = 12.sp,
                    color = TextBody,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Round 2
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Lượt 2: Phe Phản đối (AI Nova)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentSky
                    )
                    Text(text = "01:58", fontSize = 11.sp, color = TextMuted)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "\"Bản chất của phát triển công nghệ là công cụ nâng cao năng suất. Trí tuệ nhân tạo không xóa bỏ nghệ sĩ, mà trao cho họ một cây cọ vẽ số có sức mạnh tính toán vượt bậc...\"",
                    fontSize = 12.sp,
                    color = TextBody,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        ADPPPrimaryButton(
            text = "Luyện tập lại chủ đề này với AI",
            icon = Icons.Filled.Replay,
            onClick = onReSparTopic
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
