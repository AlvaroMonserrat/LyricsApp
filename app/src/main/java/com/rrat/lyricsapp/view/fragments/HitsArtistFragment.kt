package com.rrat.lyricsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.rrat.lyricsapp.databinding.FragmentHitsArtistBinding
import com.rrat.lyricsapp.model.data.ArtistData
import com.rrat.lyricsapp.viewmodel.HitsArtistViewModel
import com.rrat.lyricsapp.viewmodel.UiState


class HitsArtistFragment : Fragment() {

    private lateinit var binding: FragmentHitsArtistBinding
    private val viewModel: HitsArtistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHitsArtistBinding.inflate(inflater, container, false)
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
            viewModel.performingSingleNetworkRequest(nameArtist)
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
                onSuccess(uiState as UiState.Success<ArtistData>)
            }

            is UiState.Error->{
                onError(uiState)
            }

        }
    }

    private fun onLoad() = with(binding){
        tvArtistInformation.text = "Loading"
    }

    private fun onSuccess(uiState: UiState.Success<ArtistData>) = with(binding)
    {
        tvArtistInformation.text =  binding.etArtistByID.text.toString() + uiState.obj.response.hits.first().result.primary_artist.id

        val songList = ArrayList<String>()
        for(hits in uiState.obj.response.hits)
        {
            songList.add(hits.result.full_title)
        }
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, songList)
        lvListView.adapter = adapter

    }

    private fun onError(uiState: UiState.Error) = with(binding)
    {
        tvArtistInformation.text = uiState.message
    }
}