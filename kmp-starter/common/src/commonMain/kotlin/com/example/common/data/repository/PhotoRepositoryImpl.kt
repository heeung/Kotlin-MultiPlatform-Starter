package com.example.common.data.repository

import com.example.common.data.entity.Photo
import com.example.common.data.service.PhotoService
import com.example.common.domain.repository.PhotoRepository

internal class PhotoRepositoryImpl(
    private val photoService: PhotoService
): PhotoRepository {
    override suspend fun getPhotoList(): List<Photo> {
        return photoService.getPhotoList()
    }
}