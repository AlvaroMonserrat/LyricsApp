package com.rrat.lyricsapp.viewmodel

import com.rrat.lyricsapp.base.BaseViewModel
import com.rrat.lyricsapp.model.network.ArtistSong
import com.rrat.lyricsapp.model.network.CallbackMockApi
import com.rrat.lyricsapp.model.network.createMockApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistViewModel(private val mockApi: CallbackMockApi = createMockApi()): BaseViewModel<UiState>() {

    private var getSongByArtistCall: Call<ArtistSong>? = null

    fun getSongByIdArtist(idArtist: String)
    {
        uiState.value = UiState.Loading

        getSongByArtistCall = mockApi.getSongByIdArtist(idArtist)
        getSongByArtistCall!!.enqueue(object : Callback<ArtistSong>{
            override fun onResponse(call: Call<ArtistSong>, response: Response<ArtistSong>) {
                if (response.isSuccessful)
                {
                    val artistSong = response.body()!!
                    uiState.value = UiState.Success(artistSong)
                }else{
                    uiState.value = UiState.Error("Artist was not found.")
                }
            }

            override fun onFailure(call: Call<ArtistSong>, t: Throwable) {
                uiState.value = UiState.Error("Something unexpected happened!")
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        getSongByArtistCall?.cancel()
    }

}