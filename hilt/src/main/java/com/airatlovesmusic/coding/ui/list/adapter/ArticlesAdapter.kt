package com.airatlovesmusic.coding.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.coding.R
import com.airatlovesmusic.coding.entity.Article

class ArticlesAdapter(
    private val onClickListener: (Article) -> Unit
): RecyclerView.Adapter<ArticleHolder>() {

    private var list: List<Article> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleHolder(itemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) =
        holder.bind(list[position], onClickListener)

    fun updateList(list: List<Article>) {
        this.list = list
        notifyDataSetChanged()
    }


}