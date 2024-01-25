package com.example.common.presentation.comment.state

import com.example.common.data.entity.Comment
import com.example.common.presentation.comment.intent.CommentEvent

public data class CommentState(
    val commentList: List<Comment> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
