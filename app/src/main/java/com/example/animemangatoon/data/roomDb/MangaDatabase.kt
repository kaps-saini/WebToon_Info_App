package com.example.animemangatoon.data.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.animemangatoon.models.MangaModel

@Database(entities = [MangaModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MangaDatabase : RoomDatabase() {
    abstract fun mangaDao(): MangaDao
}
