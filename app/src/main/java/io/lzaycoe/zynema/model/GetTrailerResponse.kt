package io.lzaycoe.zynema.model


import com.google.gson.annotations.SerializedName

data class GetTrailerResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<TrailerResultList>
)