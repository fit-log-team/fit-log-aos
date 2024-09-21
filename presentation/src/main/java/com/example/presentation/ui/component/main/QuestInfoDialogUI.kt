package com.example.presentation.ui.component.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.presentation.ui.theme.color_1d1b20
import com.example.presentation.ui.theme.color_21005d
import com.example.presentation.ui.theme.color_eaddff

@Composable
fun QuestInfoDialogUI() {
    Dialog(onDismissRequest = { }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = color_eaddff, shape = RoundedCornerShape(32.dp))
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = color_1d1b20,
                        fontSize = 24.sp
                    ),
                    text = "개인 퀘스트",
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
            }
            Text(
                modifier = Modifier.padding(4.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    color = color_1d1b20
                ),
                text = "50,000 걸음을 걸으세요!\n" +
                        "- 기간: 일주일\n" +
                        "- 정원: 1명\n" +
                        "- 보상: 5포인트",
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = color_21005d
                ),
                text = "시작하기",
                textAlign = TextAlign.End
            )

        }
    }
}