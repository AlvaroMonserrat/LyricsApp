package com.rrat.lyricsapp.viewmodel

import com.rrat.lyricsapp.base.BaseViewModel
import com.rrat.lyricsapp.model.data.ArtistData
import com.rrat.lyricsapp.model.network.ArtistSong
import com.rrat.lyricsapp.model.network.CallbackMockApi
import com.rrat.lyricsapp.model.network.createMockApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HitsArtistViewModel(private val mockApi: CallbackMockApi = createMockApi()): BaseViewModel<UiState>() {

    private var getHitsArtistCall: Call<ArtistData>? = null

    fun getHitsArtist(nameArtist: String)
    {
        uiState.value = UiState.Loading

        getHitsArtistCall = mockApi.getArtist(nameArtist)
        getHitsArtistCall!!.enqueue(object : Callback<ArtistData> {
            override fun onResponse(call: Call<ArtistData>, response: Response<ArtistData>) {
                if (response.isSuccessful)
                {
                    val artistData = response.body()!!
                    uiState.value = UiState.Success(artistData)
                }else{
                    uiState.value = UiState.Error("Artist was not found.")
                }
            }

            override fun onFailure(call: Call<ArtistData>, t: Throwable) {
                uiState.value = UiState.Error("Something unexpected happened!")
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        getHitsArtistCall?.cancel()
    }

}