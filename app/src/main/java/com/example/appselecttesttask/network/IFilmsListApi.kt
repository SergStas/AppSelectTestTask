package com.example.appselecttesttask.network

import com.example.appselecttesttask.entity.FilmsListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IFilmsListApi {
    @GET("reviews/all.json")
    fun getFilms(
        @Query("api-key") apiKey: String
    ): Call<FilmsListResponse>
}