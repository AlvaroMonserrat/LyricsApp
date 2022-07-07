package com.rrat.lyricsapp.model.network

import com.rrat.lyricsapp.model.data.ArtistData
import com.rrat.lyricsapp.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface CallbackMockApi
{
    //https://genius.p.rapidapi.com/artists/16775/songs
    @Headers(
        Constants.HEADER_RAPID_API_KEY,
        Constants.HEADER_RAPID_API_HOST
    )
    @GET("artists/{idArtist}/songs")
    fun getSongByIdArtist(@Path("idArtist") idArtist: String): Call<ArtistSong>

    @Headers(
        Constants.HEADER_RAPID_API_KEY,
        Constants.HEADER_RAPID_API_HOST
    )
    @GET("/search")
    fun getArtist(@Query("q") artist: String) : Call<ArtistData>
}


fun createMockApi(): CallbackMockApi{

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(CallbackMockApi::class.java)
}