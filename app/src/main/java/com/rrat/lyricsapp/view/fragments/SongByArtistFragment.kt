package com.rrat.lyricsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.rrat.lyricsapp.databinding.FragmentSongByArtistBinding
import com.rrat.lyricsapp.model.network.ArtistSong
import com.rrat.lyricsapp.viewmodel.SongByArtistViewModel
import com.rrat.lyricsapp.viewmodel.UiState


class SongByArtistFragment : Fragment() {


    private lateinit var binding: FragmentSongByArtistBinding
    private val viewModel: SongByArtistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSongByArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState().observe(viewLifecycleOwner)
        {
                uiState->
            if(uiState != null)
            {
                render(uiState)
            }
        }

        binding.btnSearchArtist.setOnClickListener {
            val nameArtist = binding.etArtistByID.text.toString()
            //viewModel.getHitsArtist(nameArtist)
            viewModel.performingDoubleNetworkRequest(nameArtist)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun render(uiState: UiState)
    {
        when(uiState)
        {
            is UiState.Loading->{
                onLoad()
            }

            is UiState.Success<*>->{
                onSuccess(uiState as UiState.Success<ArtistSong>)
            }

            is UiState.Error->{
                onError(uiState)
            }

        }
    }

    private fun onLoad() = with(binding){
        tvArtistInformation.text = "Loading"
    }

    private fun onSuccess(uiState: UiState.Success<ArtistSong>) = with(binding)
    {
        tvArtistInformation.text =  binding.etArtistByID.text.toString()

        val songList = ArrayList<String>()
        for(song in uiState.obj.response.songs)
        {
            songList.add(song.title)
        }
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, songList)
        lvListView.adapter = adapter

    }

    private fun onError(uiState: UiState.Error) = with(binding)
    {
        tvArtistInformation.text = uiState.message
    }
}