package com.example.animemangatoon.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.animemangatoon.MangaApplication
import com.example.animemangatoon.data.roomDb.MangaDao
import com.example.animemangatoon.data.roomDb.MangaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext
        appContext: Context
    ): MangaDatabase {
        return Room.databaseBuilder(
            appContext,
            MangaDatabase::class.java,
            "manga_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMangaDao(mangaDatabase: MangaDatabase): MangaDao {
        return mangaDatabase.mangaDao()
    }
}
