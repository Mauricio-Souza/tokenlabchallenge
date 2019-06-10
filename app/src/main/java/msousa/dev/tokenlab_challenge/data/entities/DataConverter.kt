package msousa.dev.tokenlab_challenge.data.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun toString(list: List<String>?): String {
        val gson = Gson()
        if (list == null) return ""
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(values: String?) : List<String> {
        val gson = Gson()
        if (values == null) return emptyList()
        val list = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(values, list)
    }
}