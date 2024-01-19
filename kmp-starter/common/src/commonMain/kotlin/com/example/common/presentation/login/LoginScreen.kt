package com.example.common.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.common.data.local.preference.SettingRepository
import com.example.common.util.CustomColor
import io.github.aakira.napier.Napier
import com.example.common.util.PreferencesUtil
import com.example.common.util.onKeyUp

private const val TAG = "LoginScreen"
@Composable
internal fun LoginScreen(
    component: LoginComponent,
//    prefRepository: SettingRepository,
    modifier: Modifier = Modifier,
) {
    Napier.d(tag = TAG) { "onCreate" }

//    var e = prefRepository.emailPref.get()
//    var p = prefRepository.passwordPref.get()

//    var email by remember { mutableStateOf(component.e) }
//    var password by remember { mutableStateOf(component.p) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = component.e,
            modifier = Modifier
                .height(60.dp)
                .width(300.dp),
            onValueChange = { component.e = it },
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            value = component.p,
            modifier = Modifier
                .height(60.dp)
                .width(300.dp),
            onValueChange = { component.p = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = component::onLoginButtonClick,
            modifier = Modifier
                .height(60.dp)
                .width(300.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(CustomColor.DefaultButtonColor)
        ) {
            Text("Login")
        }
    }
}

