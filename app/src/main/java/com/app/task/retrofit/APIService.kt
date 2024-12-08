package com.app.task.retrofit

import retrofit2.Response
import retrofit2.http.GET
import com.google.gson.JsonElement


interface APIService {
    @GET("v3/530a8463-572b-4b24-99c1-d33f4960c02e")
    suspend fun getMedicineData() : Response<JsonElement>
}