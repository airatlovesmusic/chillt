package com.airatlovesmusic.coding.ui.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.coding.entity.Article
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(data: Article, onClickListener: (Article) -> Unit) {
        itemView.setOnClickListener { onClickListener.invoke(data) }
        itemView.tv_title.text = data.title
        itemView.tv_url.text = data.url
    }

}