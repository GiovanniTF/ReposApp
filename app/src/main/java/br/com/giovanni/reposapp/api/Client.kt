package br.com.giovanni.reposapp.api

import android.content.Context
import android.util.Log.d
import androidx.recyclerview.widget.RecyclerView
import br.com.giovanni.reposapp.repos.ReposAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.github.com/"

object Client {

    fun createEndpoint(context: Context, recyclerView: RecyclerView) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Endpoint::class.java)

        val retrofitData = retrofitBuilder.getPosts(1)

        retrofitData.enqueue(object : Callback<ApiResponse<Posts>?> {
            override fun onResponse(
                call: Call<ApiResponse<Posts>?>,
                response: Response<ApiResponse<Posts>?>
            ) {
                val responseBody = response.body()!!

                val reposAdapter = ReposAdapter(context, responseBody)
                reposAdapter.notifyDataSetChanged()
                recyclerView.adapter = reposAdapter

            }

            override fun onFailure(call: Call<ApiResponse<Posts>?>, t: Throwable) {
                d("Client", "onFailure: " + t.message)
            }
        })
    }
}
