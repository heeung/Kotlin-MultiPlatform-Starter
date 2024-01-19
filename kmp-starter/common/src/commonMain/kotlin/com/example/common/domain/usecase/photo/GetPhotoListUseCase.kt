package com.example.common.domain.usecase.photo

import com.example.common.data.entity.Photo
import com.example.common.domain.repository.PhotoRepository

public class GetPhotoListUseCase(
    private val photoRepository: PhotoRepository
) {
    public suspend operator fun invoke(): List<Photo> = photoRepository.getPhotoList()
}