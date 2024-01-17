package com.example.common.data.service

import com.example.common.data.entity.Comment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class CommentService (
    private val httpClient: HttpClient
) {
    suspend fun getCommentList(): List<Comment> {
        val response: List<Comment> = httpClient
            .get("comments").body()
        return response
    }
}