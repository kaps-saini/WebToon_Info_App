package com.example.animemangatoon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WebToon(
    val title1:String,
    val title2:String,
    val heading:String,
    val image1:Int,
    val description:String,
    val characterList:List<Characters>
):Parcelable

@Parcelize
data class Characters(
    val id:Int,
    val image: Int,
    val title:String,
    val description: String

):Parcelable
