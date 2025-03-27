package com.statemanagementexample.statemanagementexample.b

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
fun RegisterScreenBRoot(
    viewModel: ViewModelB = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RegisterScreenB(
        state = state,
        onEmailChanged = viewModel::updateEmail,
        onPasswordChanged = viewModel::updatePassword
    )
}


@Composable
fun RegisterScreenB(
    state: RegisterScreenBState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(email = state.email, onEmailChanged = { email -> onEmailChanged(email) })
        Spacer(Modifier.height(20.dp))
        PasswordInput(
            password = state.password,
            onPasswordChanged = { password -> onPasswordChanged(password) })
        Spacer(Modifier.height(20.dp))
        RegisterButton(state.isRegisterEnable)
    }
}

@Composable
fun EmailInput(email: String, onEmailChanged: (String) -> Unit) {
    TextField(value = email, onValueChange = onEmailChanged)
}

@Composable
fun PasswordInput(password: String, onPasswordChanged: (String) -> Unit) {
    TextField(value = password, onValueChange = onPasswordChanged)
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

@Composable
@Preview(showBackground = true)
fun RegisterScreenBPreview() {
    RegisterScreenB(
        state = RegisterScreenBState(),
        onPasswordChanged = {},
        onEmailChanged = {})
}