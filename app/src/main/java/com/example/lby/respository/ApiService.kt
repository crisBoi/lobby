package com.example.lby.respository

import com.example.lby.model.Base
import retrofit2.http.GET

interface ApiService {
    @GET("repos")
    suspend fun getRepos(): ArrayList<Base>
}