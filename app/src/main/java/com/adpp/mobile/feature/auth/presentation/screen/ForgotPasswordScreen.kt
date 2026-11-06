package com.adpp.mobile.feature.auth.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LockReset
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPSecondaryButton
import com.adpp.mobile.core.designsystem.components.ADPPTextField
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.auth.presentation.viewmodel.AuthViewModel

@Composable
fun ForgotPasswordScreen(
    viewModel: AuthViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.forgotPasswordState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .imePadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
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

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.isSubmitted) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(PrimaryEmeraldLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Đã gửi liên kết khôi phục",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Chúng tôi đã gửi hướng dẫn đặt lại mật khẩu đến địa chỉ ${uiState.email.ifEmpty { "email của bạn" }}. Vui lòng kiểm tra hộp thư đến (hoặc thư mục rác).",
                    fontSize = 14.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(36.dp))

                ADPPPrimaryButton(
                    text = "Quay lại Đăng nhập",
                    onClick = {
                        viewModel.resetForgotState()
                        onNavigateToLogin()
                    }
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(PrimaryEmeraldLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.LockReset,
                    contentDescription = null,
                    tint = PrimaryEmerald,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Quên mật khẩu?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nhập địa chỉ email tài khoản của bạn để nhận liên kết đặt lại mật khẩu an toàn.",
                fontSize = 14.sp,
                color = TextSecondary,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            ADPPTextField(
                value = uiState.email,
                onValueChange = { viewModel.updateForgotEmail(it) },
                label = "Địa chỉ Email",
                placeholder = "nguyen.anh@adpp.edu.vn",
                leadingIcon = Icons.Filled.Email
            )

            Spacer(modifier = Modifier.height(28.dp))

            ADPPPrimaryButton(
                text = "Gửi liên kết khôi phục",
                isLoading = uiState.isLoading,
                onClick = { viewModel.submitForgotPassword() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ADPPSecondaryButton(
                text = "Quay lại trang Đăng nhập",
                onClick = onNavigateToLogin
            )
        }
    }
}
