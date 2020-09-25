package com.astro.yourchannel.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astro.yourchannel.R
import com.astro.yourchannel.YtPlayerActivity
import com.astro.yourchannel.models.playlistItem.PlaylistItem2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_row.view.tvOverline
import kotlinx.android.synthetic.main.main_row.view.tvTitle
import kotlinx.android.synthetic.main.playlist_item_row.view.*

class PlaylistItemAdapter(val mContext : Context) : RecyclerView.Adapter<PlaylistItemAdapter.PlaylistItemViewHolder>() {

    val TAG = "PlaylistItemAdapter"

    inner class PlaylistItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<PlaylistItem2>(){
        override fun areItemsTheSame(oldItem: PlaylistItem2, newItem: PlaylistItem2): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaylistItem2, newItem: PlaylistItem2): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.playlist_item_row,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.itemView.apply {

            tvOverline.text = currentItem.snippet.publishedAt
            tvTitle.text = currentItem.snippet.title
            tvDescription.text = currentItem.snippet.description
            try {
                Glide.with(this).load(currentItem.snippet.thumbnails.medium.url).into(ivItemThumbnail)

            }catch (e : Exception){
                e.printStackTrace()
            }


            cardViewItem.setOnClickListener {
                val gotoPlayer = Intent(mContext,YtPlayerActivity::class.java)
                gotoPlayer.putExtra("videoId",currentItem.snippet.resourceId.videoId)
                context.startActivity(gotoPlayer)
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}