package com.adpp.mobile.feature.auth.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPPasswordField
import com.adpp.mobile.core.designsystem.components.ADPPPrimaryButton
import com.adpp.mobile.core.designsystem.components.ADPPTextField
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextSecondary
import com.adpp.mobile.feature.auth.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.registerState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .imePadding()
            .verticalScroll(scrollState)
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tạo tài khoản mới",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextHeading
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Gia nhập cộng đồng tranh biện học thuật và nâng tầm tư duy phản biện cùng trợ lý AI.",
            fontSize = 14.sp,
            color = TextSecondary,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        ADPPTextField(
            value = uiState.fullName,
            onValueChange = { viewModel.updateRegisterFullName(it) },
            label = "Họ và tên",
            placeholder = "VD: Nguyễn Minh Anh",
            leadingIcon = Icons.Filled.Person
        )

        Spacer(modifier = Modifier.height(16.dp))

        ADPPTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateRegisterEmail(it) },
            label = "Địa chỉ Email",
            placeholder = "nguyen.anh@adpp.edu.vn",
            leadingIcon = Icons.Filled.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        ADPPPasswordField(
            value = uiState.password,
            onValueChange = { viewModel.updateRegisterPassword(it) },
            label = "Mật khẩu",
            placeholder = "Tạo mật khẩu bảo mật (tối thiểu 8 ký tự)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ADPPPasswordField(
            value = uiState.confirmPassword,
            onValueChange = { viewModel.updateRegisterConfirmPassword(it) },
            label = "Xác nhận mật khẩu",
            placeholder = "Nhập lại mật khẩu vừa tạo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.toggleAcceptTerms() }
        ) {
            Checkbox(
                checked = uiState.acceptedTerms,
                onCheckedChange = { viewModel.toggleAcceptTerms() },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryEmerald,
                    uncheckedColor = LightBorder,
                    checkmarkColor = LightSurface
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Tôi đồng ý với Điều khoản dịch vụ & Chính sách quyền riêng tư của nền tảng ADPP",
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ADPPPrimaryButton(
            text = "Đăng ký tài khoản",
            isLoading = uiState.isLoading,
            onClick = { viewModel.register(onSuccess = onNavigateToHome) }
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bạn đã có tài khoản?",
                fontSize = 14.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Đăng nhập ngay",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryEmerald,
                modifier = Modifier.clickable { onNavigateToLogin() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
