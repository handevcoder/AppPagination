package com.example.apppagination.util

import com.example.apppagination.model.ModelUser
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getAllUser(): Call<ModelUser>
}