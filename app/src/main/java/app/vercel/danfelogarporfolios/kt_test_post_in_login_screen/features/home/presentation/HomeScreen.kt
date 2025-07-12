package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.AuthData

@Composable
fun HomeScreen(
    onNavigate: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val authData by viewModel.authData.collectAsState(initial = AuthData.getDefaultInstance())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "User Information",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Tarjeta con la información del usuario
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // ID
                InfoRow(label = "ID:", value = authData.userInfo.id)

                // Email
                InfoRow(label = "Email:", value = authData.userInfo.email)

                // Password (oculto por seguridad)
                InfoRow(
                    label = "Password:",
                    value = authData.userInfo.password.replace(Regex("."), "*")
                )

                // Token (puede ser muy largo, así que lo limitamos)
                InfoRow(
                    label = "Token:",
                    value = authData.token.take(20) + "...",
                    maxLines = 1
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de Logout
        Button(
            onClick = { viewModel.onLogout(onNavigate) },
            colors = ButtonDefaults.buttonColors(
//                backgroundColor = MaterialTheme.colorScheme.error,
//                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(text = "Logout")
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    maxLines: Int = Int.MAX_VALUE
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}