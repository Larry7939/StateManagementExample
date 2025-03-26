package com.statemanagementexample.statemanagementexample.c

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class RegisterScreenCState(
    val email: String = "",
    val password: String = ""
)

@HiltViewModel
class ViewModelC @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenCState())
    val state = _state.asStateFlow()

    // 파생 상태: isRegisterEnable
    val isRegisterEnable: StateFlow<Boolean> = _state
        .map { it.email.isNotEmpty() && it.password.isNotEmpty() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }
}