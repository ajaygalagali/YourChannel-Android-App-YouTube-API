package com.astro.yourchannel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astro.yourchannel.R
import com.astro.yourchannel.models.searchItems.Snippet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.channel_list_row.view.*

class ChannelListAdapter : RecyclerView.Adapter<ChannelListAdapter.ChannelListViewHolder>() {

    inner class ChannelListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Snippet>(){
        override fun areItemsTheSame(oldItem: Snippet, newItem: Snippet): Boolean {
            return oldItem.channelId == newItem.channelId
        }

        override fun areContentsTheSame(oldItem: Snippet, newItem: Snippet): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelListViewHolder {
        return ChannelListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.channel_list_row,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ChannelListViewHolder, position: Int) {
        var currentItem = differ.currentList[position]

        holder.itemView.apply {

            Glide.with(this).load(currentItem.thumbnails.default.url).into(ivChannelThumbnail)
            tvChannelTitle.text = currentItem.channelTitle
            tvChannelDescription.text = currentItem.description

            ibDelChannel.setOnClickListener {
                onItemClickListener?.let { it(currentItem) }
            }

            cvChannel.setOnClickListener {
                onCardViewClickListener?.let { it(currentItem) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Snippet)->Unit)? = null
    private var onCardViewClickListener:((Snippet)->Unit)? = null

    fun setOnItemClickListener(listener:(Snippet) -> Unit){
        onItemClickListener = listener
    }

    fun setOnCardViewListener(listener:((Snippet) -> Unit)){
        onCardViewClickListener = listener
    }
}