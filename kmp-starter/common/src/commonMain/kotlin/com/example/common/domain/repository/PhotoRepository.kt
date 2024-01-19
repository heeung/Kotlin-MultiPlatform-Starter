package com.example.common.domain.repository

import com.example.common.data.entity.Photo

public interface PhotoRepository {
    public suspend fun getPhotoList(): List<Photo>
}