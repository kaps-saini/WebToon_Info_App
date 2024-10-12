package com.example.animemangatoon.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity("manga_table")
@Parcelize
data class MangaModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String,
    val desc:String,
    val imageUrl: Int,
    val ratings:Float,
    val story:WebToon
):Parcelable