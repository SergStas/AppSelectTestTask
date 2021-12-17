package com.example.appselecttesttask.network

import com.example.appselecttesttask.BuildConfig

object ApiProvider {
    val cApiClient: IFilmsListApi
        get() = RetrofitClient.getClient(BuildConfig.API_URL)
            .create(IFilmsListApi::class.java)
}