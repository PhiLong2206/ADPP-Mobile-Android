package com.adpp.mobile.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.theme.ButtonShape
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

@Composable
fun ADPPPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    val effectiveLeading = leadingIcon ?: icon

    Button(
        onClick = { if (!isLoading) onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled && !isLoading,
        shape = ButtonShape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp, pressedElevation = 2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryEmerald,
            contentColor = Color.White,
            disabledContainerColor = PrimaryEmerald.copy(alpha = 0.5f),
            disabledContentColor = Color.White.copy(alpha = 0.7f)
        ),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            if (effectiveLeading != null) {
                Icon(
                    imageVector = effectiveLeading,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun ADPPSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    val effectiveLeading = leadingIcon ?: icon

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled,
        shape = ButtonShape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightSurface,
            contentColor = TextHeading,
            disabledContainerColor = LightSurface.copy(alpha = 0.5f),
            disabledContentColor = TextMuted
        ),
        border = BorderStroke(1.dp, LightBorder),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
    ) {
        if (effectiveLeading != null) {
            Icon(
                imageVector = effectiveLeading,
                contentDescription = null,
                tint = PrimaryEmerald,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = TextHeading
        )
        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun ADPPOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    borderColor: Color = PrimaryEmerald,
    textColor: Color = PrimaryEmerald,
    icon: ImageVector? = null,
    leadingIcon: ImageVector? = null
) {
    val effectiveLeading = leadingIcon ?: icon

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(42.dp),
        enabled = enabled,
        shape = ButtonShape,
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = textColor,
            containerColor = LightSurface
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (effectiveLeading != null) {
            Icon(
                imageVector = effectiveLeading,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = textColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        )
    }
}
