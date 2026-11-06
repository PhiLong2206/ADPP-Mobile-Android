package com.adpp.mobile.core.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.theme.AccentRose
import com.adpp.mobile.core.designsystem.theme.InputShape
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun ADPPTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    readOnly: Boolean = false
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isError) AccentRose else TextHeading
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                if (placeholder.isNotEmpty()) {
                    Text(text = placeholder, color = TextMuted, fontSize = 14.sp)
                }
            },
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = if (isError) AccentRose else TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else null,
            trailingIcon = trailingIcon,
            isError = isError,
            singleLine = singleLine,
            maxLines = maxLines,
            readOnly = readOnly,
            shape = InputShape,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = LightSurface,
                unfocusedContainerColor = LightSurface,
                disabledContainerColor = LightSurface.copy(alpha = 0.5f),
                focusedBorderColor = PrimaryEmerald,
                unfocusedBorderColor = LightBorder,
                errorBorderColor = AccentRose,
                focusedTextColor = TextHeading,
                unfocusedTextColor = TextHeading,
                cursorColor = PrimaryEmerald
            )
        )
        if (isError && !errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = AccentRose,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun ADPPPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Mật khẩu",
    modifier: Modifier = Modifier,
    placeholder: String = "Nhập mật khẩu của bạn",
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isError) AccentRose else TextHeading
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholder, color = TextMuted, fontSize = 14.sp)
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = isError,
            singleLine = true,
            shape = InputShape,
            keyboardActions = keyboardActions,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = if (passwordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = LightSurface,
                unfocusedContainerColor = LightSurface,
                focusedBorderColor = PrimaryEmerald,
                unfocusedBorderColor = LightBorder,
                errorBorderColor = AccentRose,
                focusedTextColor = TextHeading,
                unfocusedTextColor = TextHeading,
                cursorColor = PrimaryEmerald
            )
        )
        if (isError && !errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = AccentRose,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ADPPSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = "Tìm kiếm chủ đề, giải đấu, người dùng...",
    modifier: Modifier = Modifier,
    onClear: () -> Unit = { onQueryChange("") }
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(text = placeholder, color = TextMuted, fontSize = 14.sp)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Tìm kiếm",
                tint = PrimaryEmerald,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Xóa tìm kiếm",
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = InputShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = LightSurface,
            unfocusedContainerColor = LightSurface,
            focusedBorderColor = PrimaryEmerald,
            unfocusedBorderColor = LightBorder,
            focusedTextColor = TextHeading,
            unfocusedTextColor = TextHeading,
            cursorColor = PrimaryEmerald
        )
    )
}
