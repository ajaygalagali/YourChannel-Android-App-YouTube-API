package com.astro.yourchannel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astro.yourchannel.R
import com.astro.yourchannel.models.playlists.PlaylistsItem1
import kotlinx.android.synthetic.main.main_row.view.*

class YtAdapter : RecyclerView.Adapter<YtAdapter.YtViewHolder>() {

    inner class YtViewHolder( itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<PlaylistsItem1>(){
        override fun areItemsTheSame(oldItem: PlaylistsItem1, newItem: PlaylistsItem1): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaylistsItem1, newItem: PlaylistsItem1): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YtViewHolder {
        return YtViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row,parent,false))
    }

    override fun onBindViewHolder(holder: YtViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.itemView.apply {

            tvOverline.text = currentItem.snippet.publishedAt
            tvTitle.text = currentItem.snippet.title
            tvDescription.text = currentItem.snippet.description

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}