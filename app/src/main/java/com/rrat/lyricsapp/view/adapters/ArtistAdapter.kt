package com.rrat.lyricsapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rrat.lyricsapp.databinding.ArtistItemBinding
import com.rrat.lyricsapp.model.network.ArtistSong
import com.rrat.lyricsapp.model.network.Song

class ArtistAdapter(private var artistSongList: List<Song>): RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ArtistItemBinding = ArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = artistSongList[position]
        holder.tvArtistName.text = song.artist_names
        holder.tvSongName.text = song.full_title
    }


    override fun getItemCount(): Int = artistSongList.size

    class ViewHolder(view: ArtistItemBinding) : RecyclerView.ViewHolder(view.root){
        val tvArtistName = view.tvArtistName
        val tvSongName = view.tvSongName
    }

}