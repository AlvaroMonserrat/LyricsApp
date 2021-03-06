package com.rrat.lyricsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rrat.lyricsapp.R
import com.rrat.lyricsapp.databinding.FragmentMenuBinding
import com.rrat.mymathlibrary.SpecialMath

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDate.text = SpecialMath().areRect(2,5).toString()

        binding.btnFragmentSearchArtistByID.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_artistFragment) }
        binding.btnFragmentSearchArtistByName.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_hitsArtistFragment) }
        binding.btnFragmentSearchSongByNameArtist.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_songByArtistFragment) }

    }


}