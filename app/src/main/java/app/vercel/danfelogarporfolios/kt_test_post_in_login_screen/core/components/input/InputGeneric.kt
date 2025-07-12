package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.ui.theme.Shapes

@Composable
fun InputGeneric(
    modifier: Modifier = Modifier,
    label: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    labelExample: String? = null,
    leftIcon: ImageVector? = null,
    leftIconAct: (() -> Unit)? = null,
    rightIconAct: (() -> Unit)? = null,
    rightIcon: ImageVector? = null,
    isPassword: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    onReturnAction: (() -> Unit)? = null,
    errorMessage: String? = null,
) {
    //state
    val isPasswordShow = rememberSaveable { mutableStateOf(false) }
    //ui
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        label?.let {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
        BasicTextField(
            modifier = modifier
                .fillMaxWidth(1f)
                .padding(0.dp),
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = if (isPassword && !isPasswordShow.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = if (isPassword) {
                keyboardOptions.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = if (singleLine) ImeAction.Done else ImeAction.Default
                )
            } else {
                keyboardOptions
            },
            keyboardActions = KeyboardActions(
                onDone = { onReturnAction?.invoke() }
            ),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = Shapes.medium
                        )
                        .border(
                            width = 2.dp,
                            color = if( errorMessage != null) MaterialTheme.colorScheme.error else Color.Transparent,
                            shape = Shapes.medium
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leftIcon != null) {
                        Icon(
                            imageVector = leftIcon,
                            contentDescription = "left-Icon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { leftIconAct?.invoke() }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp, vertical = 18.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty() && labelExample != null) {
                            Text(
                                text = labelExample,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                                )
                            )
                        }
                        innerTextField()
                    }
                    if (isPassword || rightIcon != null) {
                        Icon(
                            imageVector = when {
                                isPassword && isPasswordShow.value -> Icons.Default.VisibilityOff
                                isPassword && !isPasswordShow.value -> Icons.Default.Visibility
                                else -> rightIcon ?: Icons.Outlined.Email
                            },
                            contentDescription = "Right-Icon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    if (isPassword) {
                                        isPasswordShow.value = !isPasswordShow.value
                                    }
                                    rightIconAct?.invoke()
                                }
                        )
                    }
                }
            }
        )
        errorMessage?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
            )
        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 412,

    )
@Composable
fun InputGenericPreview() {
    InputGeneric(
        label = "Email",
        leftIcon = Icons.Default.Home,
        rightIcon = Icons.Default.Lock,
        value = "asdas",
        onValueChange = {},
        modifier = Modifier
            .padding(16.dp)
    )
}