package com.example.appselecttesttask.network

import com.example.appselecttesttask.entity.FilmData
import com.example.appselecttesttask.entity.FilmsListResponse
import com.example.appselecttesttask.network.converters.deserializers.FilmDataDeserializer
import com.example.appselecttesttask.network.converters.deserializers.FilmsListResponseDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.google.gson.GsonBuilder


object RetrofitClient {
    private var retrofit: Retrofit? = null
    private var converterFactory: GsonConverterFactory? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(getConverterFactory())
                .build()
        }
        return retrofit!!
    }

    private fun getConverterFactory(): GsonConverterFactory {
        if (converterFactory == null) {
            converterFactory = GsonConverterFactory.create(
                GsonBuilder().run {
                    registerTypeAdapter(FilmData::class.java, FilmDataDeserializer())
                    registerTypeAdapter(FilmsListResponse::class.java, FilmsListResponseDeserializer())
                }.create()
            )
        }
        return converterFactory!!
    }
}