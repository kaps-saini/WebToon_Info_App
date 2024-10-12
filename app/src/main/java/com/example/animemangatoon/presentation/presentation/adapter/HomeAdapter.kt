package com.example.animemangatoon.presentation.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animemangatoon.R
import com.example.animemangatoon.models.MangaModel

class HomeAdapter(
    private val onItemClick:(MangaModel) -> Unit
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val mangaImage = view.findViewById<ImageView>(R.id.iv_manga)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val description = view.findViewById<TextView>(R.id.tv_desc)

        fun bind(mangaModel: MangaModel){
            title.text = mangaModel.name
            description.text = mangaModel.desc
            mangaImage.setImageResource(mangaModel.imageUrl)
        }
    }

    private val diffUtil = object :DiffUtil.ItemCallback<MangaModel>(){
        override fun areItemsTheSame(oldItem: MangaModel, newItem: MangaModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MangaModel, newItem: MangaModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_manga_list,parent,false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        val currentData = differ.currentList[position]
        holder.bind(currentData)

        holder.itemView.setOnClickListener {
            onItemClick(currentData)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}