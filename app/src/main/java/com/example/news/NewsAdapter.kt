package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val items = ArrayList<News>()

    class NewsViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val titleView: TextView
        val author: TextView
        val image: ImageView

        init {
            titleView = item.findViewById(R.id.tvTitle)
            author = item.findViewById(R.id.tvAuthor)
            image = item.findViewById(R.id.ivNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = items[position]
        holder.titleView.text = currentNews.title
        holder.author.text = currentNews.author

        Glide.with(holder.itemView)
            .load(currentNews.url)
            .into(holder.image)
    }

    override fun getItemCount(): Int = items.size
}