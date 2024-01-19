package com.example.common.presentation.photo.state

import com.example.common.data.entity.Photo

public data class PhotoState(
    val photoList: List<Photo> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)