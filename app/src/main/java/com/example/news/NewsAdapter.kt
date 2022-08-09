package com.example.news

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(
    private var titles: List<String>,
    private var details: List<String>,
    private var image: List<String>,
    private var url: List<String>,
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val itemDetails: TextView = itemView.findViewById(R.id.tvDetails)
        val itemImage: ImageView = itemView.findViewById(R.id.ivNews)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val intent = Intent(Intent.ACTION_VIEW)

                intent.data = Uri.parse(url[position])
                startActivity(itemView.context, intent, null)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetails.text = details[position]

        Glide.with(holder.itemImage)
            .load(image[position])
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int = titles.size

}