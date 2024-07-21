package com.example.address


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DaDataApi {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Token 424584d420141ece3fab380c45d791b225433115",
        "X-Secret: 2ebadaf5517db355afed2d5c0bb0dd99ddb0f3e6"
    )
    @POST("v2/clean/address")
    fun cleanAddress(@Body request: List<String>): Call<List<CleanAddressResponse>>
}


