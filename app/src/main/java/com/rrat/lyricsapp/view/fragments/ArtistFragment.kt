package com.rrat.lyricsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrat.lyricsapp.R
import com.rrat.lyricsapp.databinding.FragmentArtistBinding
import com.rrat.lyricsapp.model.network.ArtistSong
import com.rrat.lyricsapp.view.adapters.ArtistAdapter
import com.rrat.lyricsapp.viewmodel.ArtistViewModel
import com.rrat.lyricsapp.viewmodel.UiState


class ArtistFragment : Fragment() {

    private lateinit var binding: FragmentArtistBinding

    private val viewModel: ArtistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArtistBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.uiState().observe(viewLifecycleOwner)
        {
            uiState->
            if(uiState != null)
            {
                render(uiState)
            }
        }

        binding.btnSearchArtist.setOnClickListener {
            val idArtist = binding.etArtistByID.text.toString()
            viewModel.getSongByIdArtist(idArtist)
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
        tvArtistInformation.text =  uiState.obj.response.next_page.toString()
        val adapter = ArtistAdapter(uiState.obj.response.songs)
        rvListView.adapter = adapter

    }

    private fun onError(uiState: UiState.Error) = with(binding)
    {
        tvArtistInformation.text = uiState.message
    }

    private fun setupRecyclerView()
    {
        binding.rvListView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    }

}