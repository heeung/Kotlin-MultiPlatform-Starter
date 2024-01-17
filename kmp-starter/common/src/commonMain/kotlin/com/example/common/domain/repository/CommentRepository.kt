package com.example.common.domain.repository

import com.example.common.data.entity.Comment

public interface CommentRepository {
    public suspend fun getCommentList(): List<Comment>
}