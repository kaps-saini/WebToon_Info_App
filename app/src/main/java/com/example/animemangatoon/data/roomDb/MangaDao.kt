package com.example.animemangatoon.data.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.animemangatoon.models.MangaModel

@Dao
interface MangaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManga(manga: MangaModel)

    @Query("SELECT * FROM manga_table")
    fun getAllManga(): LiveData<List<MangaModel>>

    @Query("SELECT * FROM manga_table WHERE id = :mangaId")
    suspend fun getMangaById(mangaId: Int): MangaModel?

    @Query("UPDATE manga_table SET ratings = :newRating WHERE id = :mangaId")
    suspend fun updateMangaRating(mangaId: Int, newRating: Float)

    @Delete
    suspend fun deleteManga(manga: MangaModel)
}
