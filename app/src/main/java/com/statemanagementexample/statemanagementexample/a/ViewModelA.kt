package com.statemanagementexample.statemanagementexample.a

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ViewModelA @Inject constructor() : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun updateEmail(email: String) {
        _email.update { email }
    }

    fun updatePassword(password: String) {
        _password.update { password }
    }

    private val isEmailValid = email
        .map { it.isNotEmpty() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )
    private val isPasswordValid = password
        .map { it.isNotEmpty() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    val isRegisterEnable = combine(isEmailValid, isPasswordValid
    ) { isEmailValid, isPasswordValid ->
        isEmailValid && isPasswordValid
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )
}