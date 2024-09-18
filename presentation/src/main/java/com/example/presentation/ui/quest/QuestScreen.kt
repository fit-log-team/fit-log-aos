package com.example.presentation.ui.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.model.Quest
import com.example.presentation.model.questes
import com.example.presentation.ui.component.SegmentedButton
import com.example.presentation.ui.component.SegmentedButtonItem
import com.example.presentation.ui.component.TitleBoldText
import com.example.presentation.viewmodel.QuestViewModel
import timber.log.Timber

/**
 * 퀘스트
 */
@Composable
fun QuestScreen(
    viewModel: QuestViewModel = hiltViewModel(),
) {
    var questType by remember { mutableStateOf(Total) }
    val questList = remember { questes }

    Box(
        modifier = Modifier
            .background(
                color = Color.White
            )
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column {
            TitleBoldText(
                title = "퀘스트",
            )

            Text(
                text = "퀘스트를 깨고 포인트를 모으세요!",
                color = Color(0xFF65558F)
            )

            Spacer(modifier = Modifier.height(20.dp))

            SegmentedButton {
                SegmentedButtonItem(
                    label = {
                        Text(text = Total)
                    },
                    selected = questType == Total,
                    icon = {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "퀘스트 전체 체크 아이콘"
                        )
                    },
                    onClick = {
                        // TODO: `전체` 퀘스트 조회
                        Timber.i("전체 퀘스트 조회")
                        questType = Total
                    }
                )
                SegmentedButtonItem(
                    label = {
                        Text(text = OnGoing)
                    },
                    selected = questType == OnGoing,
                    icon = {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "퀘스트 전체 체크 아이콘"
                        )
                    },
                    onClick = {
                        // TODO: `진행 중` 퀘스트 조회
                        Timber.i("진행 중 퀘스트 조회")
                        questType = OnGoing
                    }
                )
                SegmentedButtonItem(
                    label = {
                        Text(text = Clear)
                    },
                    selected = questType == Clear,
                    icon = {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "퀘스트 전체 체크 아이콘"
                        )
                    },
                    onClick = {
                        // TODO: 완료 퀘스트 조회
                        Timber.i("완료 퀘스트 조회")
                        questType = Clear
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            QuestList(
                questList = questList
            ) { id ->
                Timber.i("$id 클릭")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.QuestList(questList : SnapshotStateList<Quest>, onClickAction : (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.weight(1f)
    ) {
        items(
            items = questList,
            key = { it.id }
        ) { data ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.White)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                ),
                onClick = {
                    onClickAction(data.id)
                },
            ) {
                Text(
                    text = data.toString(),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


const val Total = "전체"
const val OnGoing = "진행 중"
const val Clear = "완료"

@Composable
@Preview
fun QuestScreenPreview() {
    QuestScreen()
}