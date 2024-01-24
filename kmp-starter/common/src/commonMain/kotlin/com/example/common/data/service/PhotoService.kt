package com.example.common.data.service

import com.example.common.data.entity.Photo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultTransformers
import io.ktor.client.plugins.websocket.ws
import io.ktor.client.request.get
import io.ktor.client.request.request

internal class PhotoService(
    private val httpClient: HttpClient
) {
    suspend fun getPhotoList(): List<Photo> {
        val response: List<Photo> = httpClient
            .get("photos").body()
        return response
    }
}