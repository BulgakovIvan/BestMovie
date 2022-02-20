package com.bulgakov.bestmovie.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("reviews/all.json")
    suspend fun getMovies(
        @Query("api-key") apiKey: String,
        @Query("offset") offset: Int,
        @Query("order") order: String
    ): MovieResponse

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"

        fun create(): MovieService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }
}