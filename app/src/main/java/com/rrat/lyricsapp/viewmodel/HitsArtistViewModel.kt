package com.rrat.lyricsapp.viewmodel

import androidx.lifecycle.viewModelScope
import com.rrat.lyricsapp.base.BaseViewModel
import com.rrat.lyricsapp.model.network.CoroutinesMockApi
import com.rrat.lyricsapp.model.network.createCoroutinesMockApi
import kotlinx.coroutines.launch

class HitsArtistViewModel(private val mockApi: CoroutinesMockApi = createCoroutinesMockApi()): BaseViewModel<UiState>() {


    fun performingSingleNetworkRequest(nameArtist: String)
    {
        uiState.value = UiState.Loading
        viewModelScope.launch{
            try {
                val artistData = mockApi.getArtist(nameArtist)
                uiState.value = UiState.Success(artistData)
            }catch (exception: Exception)
            {
                uiState.value = UiState.Error("Network request failed!")
            }

        }

    }

    /*
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
        */
    /*
    override fun onCleared() {
        super.onCleared()
        getHitsArtistCall?.cancel()
    }
    */


}