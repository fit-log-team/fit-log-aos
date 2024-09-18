package com.example.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf

val questes = mutableStateListOf(
    Quest(1, "퀘스트1", "진행 중"),
    Quest(2, "퀘스트2", "완료"),
    Quest(3, "퀘스트3", "진행 중"),
    Quest(4, "퀘스트4", "진행 중"),
    Quest(5, "퀘스트5", "진행 중"),
    Quest(6, "퀘스트6", "진행 중"),
    Quest(7, "퀘스트7", "진행 중"),
    Quest(8, "퀘스트8", "진행 중"),
    Quest(9, "퀘스트9", "진행 중"),
    Quest(10, "퀘스트10", "진행 중"),
    Quest(11, "퀘스트11", "완료"),
    Quest(12, "퀘스트12", "진행 중"),
    Quest(13, "퀘스트13", "진행 중"),
    Quest(14, "퀘스트14", "진행 중"),
    Quest(15, "퀘스트15", "완료"),
    Quest(16, "퀘스트16", "진행 중"),
    Quest(17, "퀘스트17", "진행 중"),
    Quest(18, "퀘스트18", "진행 중"),
    Quest(19, "퀘스트19", "진행 중"),
    Quest(20, "퀘스트20", "진행 중"),
    Quest(21, "퀘스트21", "진행 중"),
    Quest(22, "퀘스트22", "진행 중"),
)

@Immutable
data class Quest(
    val id : Int,
    val questTitle : String,
    val questState : String
)
