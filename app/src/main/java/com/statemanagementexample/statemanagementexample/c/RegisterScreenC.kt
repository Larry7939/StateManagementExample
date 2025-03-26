package com.statemanagementexample.statemanagementexample.c

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RegisterScreenCRoot(
    viewModel: ViewModelC = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val isRegisterEnable by viewModel.isRegisterEnable.collectAsStateWithLifecycle()

    RegisterScreenC(
        state = state,
        isRegisterEnable = isRegisterEnable,
        onEmailChanged = viewModel::updateEmail,
        onPasswordChanged = viewModel::updatePassword
    )
}

@Composable
fun RegisterScreenC(
    state: RegisterScreenCState,
    isRegisterEnable: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(email = state.email, onEmailChanged = onEmailChanged)
        Spacer(Modifier.height(20.dp))
        PasswordInput(password = state.password, onPasswordChanged = onPasswordChanged)
        Spacer(Modifier.height(20.dp))
        RegisterButton(isRegisterEnable)
    }
}

@Composable
fun EmailInput(email: String, onEmailChanged: (String) -> Unit) {
    TextField(value = email, onValueChange = onEmailChanged, label = { Text("Email") })
}

@Composable
fun PasswordInput(password: String, onPasswordChanged: (String) -> Unit) {
    TextField(value = password, onValueChange = onPasswordChanged, label = { Text("Password") })
}

@Composable
fun RegisterButton(isRegisterEnable: Boolean) {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        onClick = { Toast.makeText(context, "Register", Toast.LENGTH_SHORT).show() },
        enabled = isRegisterEnable
    ) {
        Text("Register")
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenCPreview() {
    RegisterScreenC(
        state = RegisterScreenCState(email = "test", password = "1234"),
        isRegisterEnable = true,
        onEmailChanged = {},
        onPasswordChanged = {}
    )
}