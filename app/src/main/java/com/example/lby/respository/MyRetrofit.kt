package com.example.lby.respository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit {

    companion object {
        private val BASE_URL = "https://api.github.com/users/octocat/"

        private var retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())


        private var retrofit = retrofitBuilder
            .build()
            .create(ApiService::class.java)

        fun getInstance(): ApiService {
            return retrofit
        }
    }

}