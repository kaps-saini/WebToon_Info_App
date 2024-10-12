package com.example.animemangatoon.presentation.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.animemangatoon.R
import com.example.animemangatoon.models.Characters
import com.example.animemangatoon.models.MangaModel

class CharacterAdapter :RecyclerView.Adapter<CharacterAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.ivCharacter)
        val title = view.findViewById<TextView>(R.id.tv_character_name)
        val description = view.findViewById<TextView>(R.id.tv_character_desc)

        fun bind(character: Characters){
            title.text = character.title
            description.text = character.description
            image.setImageResource(character.image)
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Characters>(){
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_character_list,parent,false))
    }

    override fun onBindViewHolder(holder: CharacterAdapter.HomeViewHolder, position: Int) {
        val currentData = differ.currentList[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}