package com.example.common.domain.usecase.comment

import com.example.common.data.entity.Comment
import com.example.common.domain.repository.CommentRepository

public class GetCommentListUseCase(
    private val commentRepository: CommentRepository
) {
    public suspend operator fun invoke(): List<Comment> = commentRepository.getCommentList()
}