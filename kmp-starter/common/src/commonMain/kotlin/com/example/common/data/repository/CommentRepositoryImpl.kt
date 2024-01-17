package com.example.common.data.repository

import com.example.common.data.entity.Comment
import com.example.common.data.service.CommentService
import com.example.common.domain.repository.CommentRepository

internal class CommentRepositoryImpl(
    private val commentService: CommentService
): CommentRepository {
    override suspend fun getCommentList(): List<Comment> {
        return commentService.getCommentList()
    }
}