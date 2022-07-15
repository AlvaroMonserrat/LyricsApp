package com.rrat.lyricsapp.viewmodel

import com.rrat.lyricsapp.model.data.ArtistData
import com.rrat.lyricsapp.model.network.ArtistSong
import com.rrat.lyricsapp.model.network.CoroutinesMockApi

class FakeSuccessApi : CoroutinesMockApi{
    override suspend fun getSongByIdArtist(idArtist: String): ArtistSong {
        TODO("Not yet implemented")
    }

    override suspend fun getArtist(artist: String): ArtistData {
        TODO("Not yet implemented")
    }

}
