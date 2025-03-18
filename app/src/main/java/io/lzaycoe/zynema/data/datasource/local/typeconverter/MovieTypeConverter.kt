package io.lzaycoe.zynema.data.datasource.local.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.lzaycoe.zynema.data.model.moviedetail.Genre

class MovieTypeConverter {
  private val gson: Gson by lazy { Gson() }

  @TypeConverter fun fromGenreList(value: List<Genre>): String = gson.toJson(value)

  @TypeConverter
  fun toGenreList(value: String): List<Genre> =
      gson.fromJson(value, object : TypeToken<List<Genre>>() {}.type)
}
