package com.hunseong.recycle.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hunseong.recycle.databinding.ItemArticleBinding
import java.text.SimpleDateFormat

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ViewHolder>(diffUtil) {
    class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) = with(binding) {
            val format = SimpleDateFormat("MM월 dd일")
            titleTv.text = article.title
            dateTv.text = format.format(article.createdAt)
            priceTv.text = article.price

            if (article.imageUrl.isNotEmpty()) {
                Glide.with(thumbnailIv)
                    .load(article.imageUrl)
                    .into(thumbnailIv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Article>() {

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.createdAt == newItem.createdAt
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }
}