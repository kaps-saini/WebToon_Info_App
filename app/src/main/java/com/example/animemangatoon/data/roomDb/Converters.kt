package com.example.animemangatoon.data.roomDb

import androidx.room.TypeConverter
import com.example.animemangatoon.models.WebToon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromWebToon(webToon: WebToon): String {
        val gson = Gson()
        return gson.toJson(webToon)
    }

    @TypeConverter
    fun toWebToon(webToonString: String): WebToon {
        val gson = Gson()
        val type = object : TypeToken<WebToon>() {}.type
        return gson.fromJson(webToonString, type)
    }
}
