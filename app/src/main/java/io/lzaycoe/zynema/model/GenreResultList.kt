package io.lzaycoe.zynema.model


import com.google.gson.annotations.SerializedName

data class GenreResultList(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)