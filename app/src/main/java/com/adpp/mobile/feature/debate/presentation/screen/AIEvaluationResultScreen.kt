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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPSecondaryButton
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.components.ScoreCard
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.AccentAmberLight
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.debate.presentation.viewmodel.DebateViewModel

@Composable
fun AIEvaluationResultScreen(
    viewModel: DebateViewModel,
    onNavigateBack: () -> Unit,
    onDebateAgain: () -> Unit,
    onReturnToHome: () -> Unit
) {
    val evaluationState by viewModel.evaluationState.collectAsState()
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
                text = "Báo cáo đánh giá phiên tranh biện",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
            IconButton(
                onClick = {},
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Chia sẻ kết quả",
                    tint = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Banner Điểm Tổng Quan
        ADPPCard(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = LightSurface,
            borderColor = PrimaryEmerald.copy(alpha = 0.4f),
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(PrimaryEmeraldLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.EmojiEvents,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(34.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                ADPPStatusBadge(
                    text = "KẾT QUẢ: CHIẾN THẮNG",
                    variant = BadgeVariant.SUCCESS,
                    showDot = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Điểm tổng quan: ${evaluationState.overallScore}/100",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )

                Text(
                    text = "Xếp loại: Xuất sắc • Dữ liệu học tập minh họa",
                    fontSize = 13.sp,
                    color = PrimaryEmerald,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Nhận xét chung của trọng tài AI
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Psychology,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Nhận xét chung của trọng tài",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = evaluationState.adjudicatorVerdict,
                    fontSize = 13.sp,
                    color = TextBody,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Bảng phân tích 4 tiêu chuẩn học tập
        Text(
            text = "Phân tích tiêu chuẩn năng lực",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ScoreCard(
                criterion = "Tư duy logic",
                score = evaluationState.logicScore,
                feedback = evaluationState.logicFeedback
            )
            ScoreCard(
                criterion = "Khả năng phản biện",
                score = evaluationState.refutationScore,
                feedback = evaluationState.refutationFeedback
            )
            ScoreCard(
                criterion = "Chất lượng lập luận",
                score = evaluationState.argumentQualityScore,
                feedback = evaluationState.argumentQualityFeedback
            )
            ScoreCard(
                criterion = "Sử dụng dẫn chứng",
                score = evaluationState.evidenceScore,
                feedback = evaluationState.evidenceFeedback
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Điểm mạnh & Điểm cần cải thiện
        ADPPCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Điểm mạnh
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Điểm mạnh nổi bật",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                evaluationState.keyStrengths.forEach { str ->
                    Row(
                        modifier = Modifier.padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            tint = PrimaryEmerald,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = str,
                            fontSize = 13.sp,
                            color = TextBody,
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Điểm cần cải thiện
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Lightbulb,
                        contentDescription = null,
                        tint = AccentAmber,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Điểm cần cải thiện",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                evaluationState.recommendations.forEach { rec ->
                    Row(
                        modifier = Modifier.padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "•",
                            fontSize = 14.sp,
                            color = AccentAmber,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = rec,
                            fontSize = 13.sp,
                            color = TextBody,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Luyện tập lại & Về trang chủ
        ADPPPrimaryButton(
            text = "Luyện tập lại chủ đề khác",
            onClick = onDebateAgain
        )

        Spacer(modifier = Modifier.height(10.dp))

        ADPPSecondaryButton(
            text = "Về trang chủ",
            onClick = onReturnToHome
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
