package com.example.appselecttesttask.data

import com.example.appselecttesttask.BuildConfig
import com.example.appselecttesttask.entity.FilmsListResponse
import com.example.appselecttesttask.network.ApiProvider
import retrofit2.Call
import java.lang.Exception

class FilmsDataSource: IFilmsDataSource {
    override fun getFilmsCall(): Call<FilmsListResponse>? {
        return try {
            ApiProvider.cApiClient.getFilms(BuildConfig.API_KEY)
        } catch (e: Exception) {
            null
        }
    }
}