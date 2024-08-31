package com.example.presentation.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.sharp.List
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.component.NavigatorMenu
import com.example.presentation.ui.component.SearchBox
import com.example.presentation.util.PermissionUtils
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun MainScreen2() {
    val items = listOf(
        Pair("내 정보", Icons.Sharp.Person),
        Pair("퀘스트", Icons.Rounded.Star),
        Pair("피드", Icons.Rounded.FavoriteBorder),
        Pair("랭킹", Icons.Sharp.List),
    )

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(200.dp)
            ) {
                Text("User NickName", modifier = Modifier.padding(28.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        label = { NavigatorMenu(title = item.first, imageVector = item.second) },
                        selected = item.first == selectedItem.first,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFFE8DEF8),
                            selectedIconColor = Color.Black,
                            unselectedIconColor = Color(0xFF49454F),
                            selectedTextColor = Color.Black,
                            unselectedTextColor = Color(0xFF49454F),
                        ),
                        onClick = {
                            selectedItem = item
                            when (item.first) {
                                //_ TODO 각 메뉴별 클릭 이벤트
                            }
                        },
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("로그아웃")
                        }
                    },
                    color = Color(0xFF65558F),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            //_ TODO 로그아웃 기능
                        },
                )
            }
        }
    ) {
        SearchBox(
            modifier = Modifier
                .padding(top = 31.dp)
                .padding(horizontal = 20.5.dp)
                .fillMaxWidth()
                .height(56.dp),
            clickMenu = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            },
            clickSearch = {
                //_ TODO 검색 버튼 클릭
            }
        )
    }
}