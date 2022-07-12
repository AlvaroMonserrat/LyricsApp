package com.rrat.lyricsapp.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rrat.lyricsapp.base.BaseViewModel
import com.rrat.lyricsapp.model.network.CoroutinesMockApi
import com.rrat.lyricsapp.model.network.createCoroutinesMockApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import timber.log.Timber

class HitsArtistViewModel(private val mockApi: CoroutinesMockApi = createCoroutinesMockApi()): BaseViewModel<UiState>() {


    fun performingSingleNetworkRequest(nameArtist: String)
    {
        uiState.value = UiState.Loading

        val job = viewModelScope.launch{
            Log.i("VIEWMODEL", "I am the first statement in the coroutine.")
            try {
                val artistData = mockApi.getArtist(nameArtist)
                uiState.value = UiState.Success(artistData)
            }catch (exception: Exception)
            {
                //Timber.e(exception)
                uiState.value = UiState.Error("Network request failed!")
            }

        }
        Log.i("VIEWMODEL", "I am the first statement after launching the coroutine.")
        job.invokeOnCompletion {
            throwable->
            if (throwable is CancellationException)
            {
                Log.i("VIEWMODEL","Coroutine was cancelled.")
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

    override fun onCleared() {
        super.onCleared()
        Log.i("VIEWMODEL", "CLEAR")
    }
}