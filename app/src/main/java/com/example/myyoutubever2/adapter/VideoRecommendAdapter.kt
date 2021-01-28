package com.example.myyoutubever2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myyoutubever2.R
import com.example.myyoutubever2.database.entity.VideoDB
import kotlinx.android.synthetic.main.adapter_video_recommend.view.*

class VideoRecommendAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = arrayListOf<VideoDB>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            videoInformationType -> VideoInformationViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_information, parent, false)
            )

            else -> VideoRecommendViewHolder (
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_recommend, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder as? VideoInformationViewHolder != null) {
            holder.bind()
        }else if(holder as? VideoRecommendViewHolder != null) {
            holder.bind(items[position-1])
        }

    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun getItemViewType(position: Int)
            = if(position == 0) videoInformationType else videoRecommendType

    fun setRecommendVideoList(recommendVideoDB: List<VideoDB>) {
        items.clear()
        items.addAll(recommendVideoDB)
    }

    inner class VideoInformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    inner class VideoRecommendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(videoDB: VideoDB) {
            itemView.videoPreview.setVideoPreview(videoDB)
        }
    }

    companion object {
        const val videoInformationType = 1
        const val videoRecommendType = 2
    }
}