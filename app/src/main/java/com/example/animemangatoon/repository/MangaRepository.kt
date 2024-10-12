package com.example.animemangatoon.repository

import androidx.lifecycle.LiveData
import com.example.animemangatoon.data.roomDb.MangaDao
import com.example.animemangatoon.models.MangaModel
import javax.inject.Inject

class MangaRepository @Inject constructor(
    private val mangaDao: MangaDao
) {
    suspend fun insertManga(manga: MangaModel) = mangaDao.insertManga(manga)

    fun getAllManga(): LiveData<List<MangaModel>> {
        return mangaDao.getAllManga()
    }

    suspend fun deleteManga(manga: MangaModel) = mangaDao.deleteManga(manga)

    suspend fun getMangaById(mangaId: Int): MangaModel? {
        return mangaDao.getMangaById(mangaId)
    }

    suspend fun updateMangaRating(mangaId: Int, newRating: Float) {
        mangaDao.updateMangaRating(mangaId, newRating)
    }
}
