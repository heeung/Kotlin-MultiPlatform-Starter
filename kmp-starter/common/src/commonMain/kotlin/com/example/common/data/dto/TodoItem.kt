package com.example.common.data.dto

internal data class TodoItem(
    val id: Long = 0L,
    val text: String = "",
    val isDone: Boolean = false
)