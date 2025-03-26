package com.statemanagementexample.statemanagementexample.b

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class RegisterScreenBState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isRegisterEnable: Boolean = false,
)

@HiltViewModel
class ViewModelB @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(RegisterScreenBState())
    val state = _state.asStateFlow()

    init {
        state
            .distinctUntilChangedBy { it.email }
            .map { it.email.isNotEmpty() }
            .onEach { isEmailValid ->
                _state.update {
                    it.copy(
                        isEmailValid = isEmailValid
                    )
                }
            }.launchIn(viewModelScope)
        state
            .distinctUntilChangedBy { it.password }
            .map { it.password.isNotEmpty() }
            .onEach { isPasswordValid ->
                _state.update {
                    it.copy(
                        isPasswordValid = isPasswordValid
                    )
                }
            }.launchIn(viewModelScope)

        state.onEach { state ->
            _state.update {
                state.copy(isRegisterEnable = state.isEmailValid && state.isPasswordValid)
            }
        }.launchIn(viewModelScope)
    }

    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }
}