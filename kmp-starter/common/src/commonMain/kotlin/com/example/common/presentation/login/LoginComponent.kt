package com.example.common.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.example.common.data.local.preference.SettingRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "LoginComponent"
public class LoginComponent(
    componentContext: ComponentContext,
    public val prefRepository: SettingRepository,
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    internal var e by mutableStateOf(prefRepository.emailPref.get())
    internal var p by mutableStateOf(prefRepository.passwordPref.get())

    internal fun onLoginButtonClick() {
        prefRepository.emailPref.set(e)
        prefRepository.passwordPref.set(p)
    }

    private fun getLoginInfo() {
        e = prefRepository.emailPref.get()
        p = prefRepository.passwordPref.get()
    }

    init {
        Napier.d(tag = TAG) { "onCreate" }
        getLoginInfo()
    }
}