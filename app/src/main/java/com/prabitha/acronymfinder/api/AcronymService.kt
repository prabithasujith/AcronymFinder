package com.prabitha.acronymfinder.api

import com.prabitha.acronymfinder.models.Acronyms
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("software/acromine/dictionary.py")
    suspend fun getAcronyms(@Query("sf") query: String) : Response<List<Acronyms>>


    companion object {
        private const val BASE_URL = "http://www.nactem.ac.uk/"

        fun create(): AcronymService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AcronymService::class.java)
        }
    }
}