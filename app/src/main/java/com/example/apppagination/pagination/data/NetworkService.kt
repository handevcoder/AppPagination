package com.example.apppagination.pagination.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("https://reqres.in/api/")

    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): getAllDataUser<Response>

    companion object{
        fun getService(): NetworkService{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }

}