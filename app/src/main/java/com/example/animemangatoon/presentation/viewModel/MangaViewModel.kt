package com.example.animemangatoon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animemangatoon.models.MangaModel
import com.example.animemangatoon.repository.MangaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val repository: MangaRepository
) : ViewModel() {

    val allMangas: LiveData<List<MangaModel>> = repository.getAllManga()

    private val _isMangaInDb = MutableLiveData<Boolean>()
    val isMangaInDb: LiveData<Boolean> get() = _isMangaInDb

    fun checkMangaInDb(mangaId: Int) {
        viewModelScope.launch {
            val existingManga = repository.getMangaById(mangaId)
            if (existingManga != null) {
                _isMangaInDb.value = true
            } else {
                _isMangaInDb.value = false
            }
        }
    }

    fun insertManga(manga: MangaModel) {
        viewModelScope.launch {
            repository.insertManga(manga)
        }
    }

    fun deleteManga(manga: MangaModel) {
        viewModelScope.launch {
            repository.deleteManga(manga)
        }
    }

    fun updateMangaRating(mangaId: Int, newRating: Float) {
        viewModelScope.launch {
            repository.updateMangaRating(mangaId, newRating)
        }
    }
}
