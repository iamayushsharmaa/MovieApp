package com.example.movieapp.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonUtil {
    private val gson = Gson()

    // Convert List<String> to JSON String
    fun listToJson(list: List<String>): String {
        return gson.toJson(list)
    }

    // Convert JSON String to List<String>
    fun jsonToList(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, listType)
    }
}
