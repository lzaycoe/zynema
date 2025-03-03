package io.lzaycoe.zynema.model


import com.google.gson.annotations.SerializedName

data class GetGenresResponse(
    @SerializedName("genres")
    val genres: List<GenreResultList>
)