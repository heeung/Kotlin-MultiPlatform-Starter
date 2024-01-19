package com.example.common.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Photo(
    @SerialName("albumId")
    val albumId: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("url")
    val url: String = ""
)