package com.example.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domain.model.poi.response.Poi
import com.example.domain.model.poi.response.PoiItem
import com.example.presentation.ui.theme.color_1d1b20
import com.example.presentation.ui.theme.color_eaddff
import com.example.presentation.ui.theme.color_white
import com.example.presentation.ui.theme.primary
import com.example.presentation.ui.theme.primaryContainer

@Composable
fun LocationSearchScreen(
    keyword: String,
    onKeywordChange: (String) -> Unit,
    onBackButtonClick: () -> Unit,
) {
    val tempList = listOf(
        PoiItem(name = "화곡역", middleBizName = "지하철역"),
        PoiItem(name = "투썸플레이스", middleBizName = "카페"),
        PoiItem(name = "랄랄라", middleBizName = "헬스장"),
        PoiItem(name = "냥", middleBizName = "냥")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 33.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                onBackButtonClick()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
            }

            TextField(
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
                placeholder = {
                    Text(text = "도착지 입력")
                },
                value = keyword, onValueChange = {
                    onKeywordChange(it)
                    //_ TODO poi를 조회할건데 최종 입력된 키워드로만 조회하도록 해야함. (이전 조회는 취소 해야함.)
                })

            IconButton(onClick = {
                onKeywordChange("")
            }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
            }
        }
        Divider()
        LocationListUI(list = tempList)
    }
}

@Composable
fun LocationListUI(list: List<PoiItem>) {
    LazyColumn {
        items(list) {
            LocationItem(item = it)
        }
    }
}

@Composable
fun LocationItem(item: PoiItem) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = color_eaddff, shape = RoundedCornerShape(40.dp))
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                tint = color_1d1b20,
                contentDescription = "Location"
            )
        }
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = item.name
            )
            Text(
                text = item.middleBizName
            )
        }
    }
}