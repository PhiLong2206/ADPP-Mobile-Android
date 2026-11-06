package com.adpp.mobile.feature.onboarding.presentation.screen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import kotlinx.coroutines.launch

data class OnboardingPageData(
    val title: String,
    val subtitle: String,
    val description: String,
    val icon: ImageVector,
    val accentTag: String
)

@Composable
fun OnboardingScreen(
    onNavigateToLogin: () -> Unit
) {
    val pages = listOf(
        OnboardingPageData(
            title = "Luyện phản biện trực tiếp với AI",
            subtitle = "TRÍ TUỆ THÍCH ỨNG",
            description = "Rèn luyện phản xạ lập luận sắc bén cùng trợ lý AI thông minh qua nhiều cấp độ và thể thức học thuật mọi lúc, mọi nơi.",
            icon = Icons.Filled.Psychology,
            accentTag = "Trợ lý AI Thông minh"
        ),
        OnboardingPageData(
            title = "Đánh giá năng lực tự động tức thì",
            subtitle = "CHẤM ĐIỂM CHUẨN MỰC",
            description = "Nhận báo cáo phân tích toàn diện về tư duy logic, cấu trúc lập luận, kỹ năng phản bác và khả năng sử dụng dẫn chứng.",
            icon = Icons.Filled.Analytics,
            accentTag = "Chấm điểm Đa tiêu chí"
        ),
        OnboardingPageData(
            title = "Tranh biện trực tiếp 1vs1",
            subtitle = "ĐỐI KHÁNG THỜI GIAN THỰC",
            description = "So tài lập luận đối kháng 1v1 trực tiếp với các tranh biện viên khác, rèn luyện kỹ năng nghe và phản hồi tức thì.",
            icon = Icons.Filled.Groups,
            accentTag = "Đối kháng Trực tiếp 1v1"
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Top bar with Skip button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage < pages.size - 1) {
                TextButton(onClick = onNavigateToLogin) {
                    Text(
                        text = "Bỏ qua",
                        color = TextSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Horizontal Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { pageIndex ->
            val page = pages[pageIndex]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Feature Icon Container
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(PrimaryEmeraldLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = page.icon,
                        contentDescription = page.title,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(68.dp)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Academic Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(PrimaryEmeraldLight)
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = page.accentTag,
                        color = PrimaryEmerald,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = page.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Description
                Text(
                    text = page.description,
                    fontSize = 14.sp,
                    color = TextBody,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }

        // Bottom Controls: Page Indicator & Action Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Pager Indicator Dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(pages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(if (isSelected) 24.dp else 8.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) PrimaryEmerald else LightBorder)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Button
            val isLastPage = pagerState.currentPage == pages.size - 1

            ADPPPrimaryButton(
                text = if (isLastPage) "Bắt đầu ngay" else "Tiếp tục",
                trailingIcon = if (!isLastPage) Icons.AutoMirrored.Filled.ArrowForward else null,
                onClick = {
                    if (isLastPage) {
                        onNavigateToLogin()
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            )

            // Secondary link on last page
            AnimatedVisibility(visible = isLastPage) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    TextButton(onClick = onNavigateToLogin) {
                        Text(
                            text = "Đã có tài khoản? Đăng nhập",
                            color = PrimaryEmerald,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
