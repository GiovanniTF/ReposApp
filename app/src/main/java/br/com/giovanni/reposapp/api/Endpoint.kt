package br.com.giovanni.reposapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    fun getPosts(@Query("page") page: Int):
            Call<ApiResponse<Posts>>
}
