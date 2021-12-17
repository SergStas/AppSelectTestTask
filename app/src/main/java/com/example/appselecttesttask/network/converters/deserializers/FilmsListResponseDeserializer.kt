package com.example.appselecttesttask.network.converters.deserializers

import com.example.appselecttesttask.entity.FilmData
import com.example.appselecttesttask.entity.FilmsListResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class FilmsListResponseDeserializer: JsonDeserializer<FilmsListResponse> {
    companion object {
        private const val LIST_KEY = "results"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): FilmsListResponse {
        val jsonObject = json as JsonObject
        val jsonList = jsonObject.getAsJsonArray(LIST_KEY)
        val result = jsonList.map { filmJson ->
            context!!.deserialize(filmJson, FilmData::class.java) as FilmData
        }

        return FilmsListResponse(films = result)
    }
}