package com.example.common.presentation.state

import com.example.common.data.entity.Comment

public data class CommentState(
    val commentList: List<Comment> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
