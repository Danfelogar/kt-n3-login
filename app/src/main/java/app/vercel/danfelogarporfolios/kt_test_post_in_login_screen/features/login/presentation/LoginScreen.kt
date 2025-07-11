package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.ui.theme.Shapes

@Composable
fun LoginScreen(
    onNavigate: () -> Unit = { /* No-op */ },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 32.dp, bottom = 20.dp, start = 18.dp, end = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(160.dp)
                .height(160.dp)
                .clip(Shapes.medium)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = "APP",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,

            )
        }
        Text(
            text = "Welcome back",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp,bottom = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Log in to continue",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = "Forgot your password?",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top= 8.dp, bottom = 12.dp)
                .height(56.dp),
            shape = Shapes.medium
        ) {
            Text(
                text = "Forgot your password?",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
        Text(
            text = "Don't have an account? Sign up",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.W500,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}