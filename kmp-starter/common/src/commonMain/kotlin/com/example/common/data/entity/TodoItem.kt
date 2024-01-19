package com.example.common.data.entity

import kotlinx.serialization.Serializable

@Serializable
public data class TodoItem(
    val id: Long = 0L,
    val text: String = "",
    val isDone: Boolean = false
)