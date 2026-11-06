package com.adpp.mobile.feature.debate.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.components.ConfirmationDialog
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.AccentAmberLight
import com.adpp.mobile.core.designsystem.theme.AccentRose
import com.adpp.mobile.core.designsystem.theme.AccentRoseLight
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.AccentSkyLight
import com.adpp.mobile.core.designsystem.theme.InputShape
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.LightSurfaceVariant
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.debate.presentation.viewmodel.DebateViewModel

@Composable
fun AIDebateSessionScreen(
    viewModel: DebateViewModel,
    onNavigateBack: () -> Unit,
    onEndDebateToEvaluation: () -> Unit
) {
    val sessionState by viewModel.sessionState.collectAsState()
    val listState = rememberLazyListState()
    var showEndConfirmDialog by remember { mutableStateOf(false) }

    LaunchedEffect(sessionState.speeches.size) {
        listState.animateScrollToItem(sessionState.speeches.size)
    }

    if (showEndConfirmDialog) {
        ConfirmationDialog(
            title = "Kết thúc phiên tranh biện?",
            message = "Lập luận qua các lượt tranh biện của bạn sẽ được chuyển đến hội đồng trọng tài AI để chấm điểm và phân tích kết quả.",
            confirmText = "Xem kết quả đánh giá",
            dismissText = "Tiếp tục tranh biện",
            onConfirm = onEndDebateToEvaluation,
            onDismiss = { showEndConfirmDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .imePadding()
    ) {
        // Header phòng tranh biện: Tiêu đề, Tiến trình lượt & Đồng hồ
        Surface(
            color = LightSurface,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
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

                    // Tiến trình lượt tranh biện & Đồng hồ đếm ngược
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ADPPStatusBadge(
                            text = "Lượt ${sessionState.currentRound}/${sessionState.maxRounds}",
                            variant = BadgeVariant.PRIMARY,
                            showDot = true
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(AccentAmberLight)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Timer,
                                    contentDescription = null,
                                    tint = AccentAmber,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "01:24",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AccentAmber
                                )
                            }
                        }
                    }

                    // Nút kết thúc phiên
                    IconButton(
                        onClick = { showEndConfirmDialog = true },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Assessment,
                            contentDescription = "Kết thúc và đánh giá",
                            tint = PrimaryEmerald
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Chủ đề tranh biện
                Text(
                    text = "Chủ đề: ${sessionState.motion}",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Phân biệt 2 phe
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Bạn: Phe Ủng hộ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryEmerald
                    )
                    Text(
                        text = "Đối thủ: Phe Phản đối (AI)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AccentRose
                    )
                }
            }
        }

        // Danh sách các lượt phát biểu và lập luận
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Phiên tranh biện đã bắt đầu • Luật 3 lượt đối kháng",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
            }

            items(sessionState.speeches) { speech ->
                val isUser = speech.isUser
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (!isUser) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(AccentRoseLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Psychology,
                                    contentDescription = null,
                                    tint = AccentRose,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        Text(
                            text = speech.speaker,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isUser) PrimaryEmerald else TextHeading
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = speech.timestamp,
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (isUser) 16.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 16.dp
                        ),
                        color = if (isUser) PrimaryEmeraldLight.copy(alpha = 0.35f) else LightSurface,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isUser) PrimaryEmerald.copy(alpha = 0.5f) else LightBorder
                        ),
                        shadowElevation = 1.dp,
                        modifier = Modifier.fillMaxWidth(0.92f)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        if (speech.role == "Phe Ủng hộ") PrimaryEmeraldLight
                                        else AccentRoseLight
                                    )
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "${speech.role} • ${speech.roundTitle}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (speech.role == "Phe Ủng hộ") PrimaryEmerald else AccentRose
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = speech.argumentText,
                                fontSize = 14.sp,
                                color = TextHeading,
                                lineHeight = 21.sp
                            )
                        }
                    }
                }
            }

            // Trạng thái đang chờ AI phản hồi
            if (sessionState.isAILoading) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = PrimaryEmerald,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Trợ lý AI Nova đang phân tích lập luận và xây dựng phản biện...",
                            fontSize = 12.sp,
                            color = PrimaryEmerald,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Vùng nhập lập luận dễ thao tác (hỗ trợ micro & gửi)
        Surface(
            color = LightSurface,
            shadowElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nút micro trực quan (UI demo)
                    IconButton(
                        onClick = { /* Demo speech-to-text click */ },
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(LightSurfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Mic,
                            contentDescription = "Ghi âm giọng nói",
                            tint = PrimaryEmerald,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = sessionState.userInputText,
                        onValueChange = { viewModel.updateUserInputText(it) },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = if (sessionState.isUserTurn) "Nhập lập luận phản biện của bạn..." else "Đang chờ đối phương...",
                                fontSize = 13.sp,
                                color = TextMuted
                            )
                        },
                        enabled = sessionState.isUserTurn && !sessionState.isAILoading,
                        shape = InputShape,
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = LightSurface,
                            unfocusedContainerColor = LightSurface,
                            focusedBorderColor = PrimaryEmerald,
                            unfocusedBorderColor = LightBorder,
                            focusedTextColor = TextHeading,
                            unfocusedTextColor = TextHeading
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Nút gửi lập luận
                    IconButton(
                        onClick = { viewModel.submitUserArgument() },
                        enabled = sessionState.userInputText.isNotBlank() && sessionState.isUserTurn,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(
                                if (sessionState.userInputText.isNotBlank()) PrimaryEmerald
                                else LightBorder
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Gửi lập luận",
                            tint = if (sessionState.userInputText.isNotBlank()) Color.White else TextMuted,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nhấn gửi để AI tự động phản biện",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )

                    Text(
                        text = "Kết thúc & Chấm điểm",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryEmerald,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(PrimaryEmeraldLight)
                            .clickable { showEndConfirmDialog = true }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}
