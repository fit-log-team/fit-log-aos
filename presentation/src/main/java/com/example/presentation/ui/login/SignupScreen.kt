import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.ui.component.BackButton
import com.example.presentation.ui.component.CommonButton
import com.example.presentation.ui.component.EmailTextField
import com.example.presentation.ui.component.PasswordField

@Composable
fun SignupScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var birth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackButton(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "회원가입", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // 이메일 입력 필드
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            EmailTextField(email = email, onValueChange = { email = it }, modifier = Modifier.weight(1f))
            CommonButton(title = "중복확인", onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 8.dp, top = 8.dp))
        }

        // 비밀번호 입력 필드 (비밀번호 표시 토글 기능 추가)
        var passwordVisible by remember { mutableStateOf(false) }
        PasswordField(password = password, onValueChange = {password = it}, modifier = Modifier.fillMaxWidth())

        // 닉네임 입력 필드

        // 생년월일 입력 필드 (DatePicker를 사용하여 구현 가능)

        // 성별 선택

        // 회원가입 버튼
    }
}