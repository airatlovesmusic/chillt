package com.airatlovesmusic.coding.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airatlovesmusic.coding.entity.Article
import com.airatlovesmusic.coding.model.data.ArticlesFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel @ViewModelInject constructor(
    private val postsFetcher: ArticlesFetcher
): ViewModel() {

    val list = MutableLiveData<List<Article>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            isLoading.value = true
            val posts = withContext(Dispatchers.IO) { postsFetcher.getArticles() }
            list.value = posts
            isLoading.value = false
        }
    }

}