package com.astro.yourchannel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astro.yourchannel.R
import com.astro.yourchannel.models.searchItems.SearchItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.search_channel_row.view.*
import java.lang.Exception

class SearchChannelAdapter : RecyclerView.Adapter<SearchChannelAdapter.ChannelViewHolder>(){

    inner class ChannelViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<SearchItem>(){
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {

        return ChannelViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.search_channel_row, parent, false
                )
        )

    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.itemView.apply {

            try {
                Glide.with(this).load(currentItem.snippet.thumbnails.default.url).into(ivChannelThumbnail)
            }catch (e : Exception){
                e.printStackTrace()
            }

            tvChannelTitle.text = currentItem.snippet.channelTitle
            tvChannelDescription.text = currentItem.snippet.description


        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}