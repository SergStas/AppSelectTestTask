package com.example.appselecttesttask.network.converters.deserializers

import com.example.appselecttesttask.entity.FilmData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class FilmDataDeserializer: JsonDeserializer<FilmData> {
    companion object {
        private const val TITLE_KEY = "display_title"
        private const val DESCRIPTION_KEY = "summary_short"
        private const val IMAGE_KEY = "multimedia"
        private const val IMAGE_URL_KEY = "src"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): FilmData {
        val jsonObject = json as JsonObject
        val titleValue = jsonObject.get(TITLE_KEY).asString
        val title = if (titleValue.isNotEmpty()) titleValue else "[no review title]"
        val description = jsonObject.get(DESCRIPTION_KEY).asString
        val imageObject = jsonObject.getAsJsonObject(IMAGE_KEY)
        val url = imageObject.get(IMAGE_URL_KEY).asString

        return FilmData(title, description, url)
    }
}