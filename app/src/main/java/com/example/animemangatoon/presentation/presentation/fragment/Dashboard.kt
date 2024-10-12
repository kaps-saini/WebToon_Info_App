package com.example.animemangatoon.presentation.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animemangatoon.R
import com.example.animemangatoon.data.SampleData
import com.example.animemangatoon.databinding.FragmentDashboardBinding
import com.example.animemangatoon.databinding.FragmentFavoritesBinding
import com.example.animemangatoon.presentation.presentation.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Dashboard : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeAdapter:HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        homeAdapter.differ.submitList(SampleData.mangaList)

    }

    private fun setupRv(){
        homeAdapter = HomeAdapter{ currentManga->
            val destination = DashboardDirections.actionDashboardToDetailedFragment(currentManga)
            findNavController().navigate(destination)
        }
        binding.rvDashboard.apply {
            this.adapter = homeAdapter
            this.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}