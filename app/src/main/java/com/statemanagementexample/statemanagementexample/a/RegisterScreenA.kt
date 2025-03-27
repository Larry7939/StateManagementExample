package com.statemanagementexample.statemanagementexample.a

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
fun RegisterScreenARoot(
    viewModel: ViewModelA = hiltViewModel()
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val isRegisterEnable by viewModel.isRegisterEnable.collectAsStateWithLifecycle()

    RegisterScreenA(
        email = email,
        password = password,
        isRegisterEnable = isRegisterEnable,
        onEmailChanged = viewModel::updateEmail,
        onPasswordChanged = viewModel::updatePassword
    )
}


@Composable
fun RegisterScreenA(
    email: String,
    password: String,
    isRegisterEnable: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(email = email, onEmailChanged = onEmailChanged)
        Spacer(Modifier.height(20.dp))
        PasswordInput(password, onPasswordChanged = onPasswordChanged)
        Spacer(Modifier.height(20.dp))
        RegisterButton(isRegisterEnable)
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
fun RegisterScreenAPreview() {
    RegisterScreenA(
        email = "email",
        password = "password",
        isRegisterEnable = true,
        onPasswordChanged = {},
        onEmailChanged = {})
}