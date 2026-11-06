package com.adpp.mobile.feature.auth.presentation.screen

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPPasswordField
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPSecondaryButton
import com.adpp.mobile.core.designsystem.components.ADPPTextField
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.PrimaryEmeraldLight
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.auth.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val uiState by viewModel.loginState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .imePadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Branding Logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(PrimaryEmeraldLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Psychology,
                    contentDescription = "Logo ADPP",
                    tint = PrimaryEmerald,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "ADPP",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHeading
                )
                Text(
                    text = "Nền tảng Luyện tập Tranh biện",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Chào mừng trở lại!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Đăng nhập tài khoản của bạn để tiếp tục rèn luyện tư duy phản biện và theo dõi tiến trình học tập.",
            fontSize = 14.sp,
            color = TextSecondary,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        ADPPTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateLoginEmail(it) },
            label = "Địa chỉ Email",
            placeholder = "nguyen.anh@adpp.edu.vn",
            leadingIcon = Icons.Filled.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        ADPPPasswordField(
            value = uiState.password,
            onValueChange = { viewModel.updateLoginPassword(it) },
            label = "Mật khẩu",
            placeholder = "Nhập mật khẩu của bạn"
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { viewModel.toggleRememberMe() }
            ) {
                Checkbox(
                    checked = uiState.rememberMe,
                    onCheckedChange = { viewModel.toggleRememberMe() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PrimaryEmerald,
                        uncheckedColor = LightBorder,
                        checkmarkColor = LightSurface
                    )
                )
                Text(
                    text = "Ghi nhớ đăng nhập",
                    fontSize = 13.sp,
                    color = TextBody
                )
            }

            TextButton(onClick = onNavigateToForgotPassword) {
                Text(
                    text = "Quên mật khẩu?",
                    fontSize = 13.sp,
                    color = PrimaryEmerald,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ADPPPrimaryButton(
            text = "Đăng nhập",
            isLoading = uiState.isLoading,
            onClick = { viewModel.login(onSuccess = onNavigateToHome) }
        )

        Spacer(modifier = Modifier.height(14.dp))

        ADPPSecondaryButton(
            text = "Trải nghiệm chế độ dùng thử (Demo)",
            leadingIcon = Icons.Filled.PlayArrow,
            onClick = onNavigateToHome
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bạn chưa có tài khoản?",
                fontSize = 14.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Đăng ký ngay",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryEmerald,
                modifier = Modifier.clickable { onNavigateToRegister() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
