package com.example.suitmediatestapp.data


import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getusers(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): UsersResponse
}