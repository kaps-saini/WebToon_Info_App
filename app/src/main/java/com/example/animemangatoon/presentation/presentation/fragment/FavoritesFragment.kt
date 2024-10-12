package com.example.animemangatoon.presentation.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animemangatoon.R
import com.example.animemangatoon.data.SampleData
import com.example.animemangatoon.databinding.FragmentFavoritesBinding
import com.example.animemangatoon.presentation.presentation.adapter.HomeAdapter
import com.example.animemangatoon.presentation.viewModel.MangaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeAdapter:HomeAdapter
    private val mangaViewModel: MangaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupObservers()
    }

    private fun setupObservers() {
        mangaViewModel.allMangas.observe(viewLifecycleOwner){ data->
            homeAdapter.differ.submitList(data)
        }
    }

    private fun setupRv(){
        homeAdapter = HomeAdapter{ currentManga->
            val destination = FavoritesFragmentDirections.actionFavoritesFragmentToDetailedFragment(currentManga)
            findNavController().navigate(destination)
        }
        binding.rvFavorite.apply {
            this.adapter = homeAdapter
            this.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}