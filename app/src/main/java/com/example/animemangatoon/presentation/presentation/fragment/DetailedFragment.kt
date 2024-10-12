package com.example.animemangatoon.presentation.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animemangatoon.R
import com.example.animemangatoon.databinding.FragmentDetailedBinding
import com.example.animemangatoon.presentation.presentation.adapter.CharacterAdapter
import com.example.animemangatoon.presentation.viewModel.MangaViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedFragment : Fragment() {

    private var _binding: FragmentDetailedBinding? = null
    private val binding get() = _binding!!

    private val args: DetailedFragmentArgs by navArgs()
    private lateinit var characterAdapter:CharacterAdapter
    private val mangaViewModel: MangaViewModel by viewModels()
    private var canBeAddedToFavorites = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewsData()
        setupUserEvents()
        setupObservers()
        characterAdapter.differ.submitList(args.CurrentMangaData.story.characterList)
    }

    private fun setupObservers() {

        mangaViewModel.isMangaInDb.observe(viewLifecycleOwner) { isInDb ->
            if (isInDb) {
                binding.fabAddToFavorite.setImageResource(R.drawable.ic_action_favorite)
                canBeAddedToFavorites = false
            } else {
                binding.fabAddToFavorite.setImageResource(R.drawable.ic_action_favorite_border)
                canBeAddedToFavorites = true
            }
        }

        mangaViewModel.allMangas.observe(viewLifecycleOwner) { mangas ->
            val updatedManga = mangas.find { it.id == args.CurrentMangaData.id }
            if (updatedManga != null) {
                val rating = updatedManga.ratings.toString()
                binding.tvRatings.visibility = View.VISIBLE
                binding.ratingbar.rating = updatedManga.ratings
                binding.tvRatings.text = "$rating/5.0"
            }
        }
    }

    private fun setupViewsData(){
        binding.apply {
            ivImage.setImageResource(args.CurrentMangaData.story.image1)
            tvCurrentTitle.text = args.CurrentMangaData.story.title1
            tvCurrentTitle2.text = args.CurrentMangaData.story.title2
            tvDescription.text = args.CurrentMangaData.story.description
            tvHeading.text = args.CurrentMangaData.story.heading
            val ratings = args.CurrentMangaData.ratings
            tvRatings.text = "$ratings/5.0"
            rvCharacters.apply {
                characterAdapter = CharacterAdapter()
                adapter = characterAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    private fun setupUserEvents(){
        mangaViewModel.checkMangaInDb(args.CurrentMangaData.id)
        binding.fabAddToFavorite.setOnClickListener{
            if (canBeAddedToFavorites){
                mangaViewModel.insertManga(args.CurrentMangaData)
                binding.fabAddToFavorite.setImageResource(R.drawable.ic_action_favorite)
                Toast.makeText(requireContext(),"Successfully added to favorites",Toast.LENGTH_SHORT).show()
            }else{
                mangaViewModel.deleteManga(args.CurrentMangaData)
                binding.fabAddToFavorite.setImageResource(R.drawable.ic_action_favorite_border)
                Toast.makeText(requireContext(),"Successfully removed from favorites",Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ratingbar.setOnRatingBarChangeListener { _, newRating, _ ->
            mangaViewModel.updateMangaRating(args.CurrentMangaData.id, newRating)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}