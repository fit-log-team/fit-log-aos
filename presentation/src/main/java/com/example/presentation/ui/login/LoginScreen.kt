package com.example.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.ui.component.CommonButton
import com.example.presentation.ui.component.EmailTextField
import com.example.presentation.ui.component.PasswordField
import com.example.presentation.ui.component.UnderlineText

@Composable
fun LoginScreen(
    clickLogin: () -> Unit,
    clickGoogleLogin: () -> Unit,
    clickSignUp: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Image(
                painter = painterResource(id = R.drawable.test_image),
                contentDescription = "앱 로고",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(100.dp))

            // 구글 로그인 버튼
            CommonButton(title = "구글 로그인", clickGoogleLogin, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray, // 구분선 색상
                thickness = 1.dp // 구분선 두께
            )
            // 이메일 입력 필드
            EmailTextField(email = email, onValueChange = { email = it }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            // 비밀번호 입력 필드
            PasswordField(password = password, onValueChange = { password = it }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            // 로그인 버튼
            CommonButton("로그인 (임시로 메인화면 이동)", clickLogin, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            // 회원가입 버튼
            UnderlineText("회원가입", clickSignUp)
        }
    }
}