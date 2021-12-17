package com.example.appselecttesttask.data

import com.example.appselecttesttask.entity.FilmsListResponse
import retrofit2.Call

interface IFilmsDataSource {
    fun getFilmsCall(): Call<FilmsListResponse>?
}