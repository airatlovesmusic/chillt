package com.airatlovesmusic.coding.model.data

import com.airatlovesmusic.coding.entity.Article
import org.jsoup.Jsoup

interface ArticlesFetcher {
    fun getArticles(): List<Article>
}

class ArticlesFetcherImpl: ArticlesFetcher {

    override fun getArticles() =
        Jsoup
            .connect("https://habr.com/ru/top/weekly/")
            .get()
            .getElementsByClass("post__title_link")
            .map { Article(it.text(), it.attr("href")) }

}