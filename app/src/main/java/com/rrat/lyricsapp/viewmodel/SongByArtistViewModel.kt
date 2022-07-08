package com.rrat.lyricsapp.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rrat.lyricsapp.base.BaseViewModel
import com.rrat.lyricsapp.model.network.CoroutinesMockApi
import com.rrat.lyricsapp.model.network.createCoroutinesMockApi
import kotlinx.coroutines.launch

class SongByArtistViewModel(private val mockApi: CoroutinesMockApi = createCoroutinesMockApi()): BaseViewModel<UiState>() {
    fun performingDoubleNetworkRequest(nameArtist: String)
    {
        uiState.value = UiState.Loading
        viewModelScope.launch{
            try {
                val artistData = mockApi.getArtist(nameArtist)
                val idArtist = artistData.response.hits.first().result.primary_artist.id.toString()
                Log.i("ID", idArtist)
                val artistSong = mockApi.getSongByIdArtist(idArtist)
                uiState.value = UiState.Success(artistSong)

            }catch (exception: Exception)
            {
                uiState.value = UiState.Error("Network request failed!")
            }

        }

    }
}