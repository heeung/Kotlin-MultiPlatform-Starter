package com.example.common.presentation.comment.intent

import com.example.common.data.entity.Comment

internal sealed interface CommentEvent {
    object Loading: CommentEvent
    class Loaded(val commentList: List<Comment>): CommentEvent
}