package com.airatlovesmusic.coding.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airatlovesmusic.coding.R
import com.airatlovesmusic.coding.ui.global.BaseFragment
import com.airatlovesmusic.coding.ui.list.adapter.ArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class ListFragment: BaseFragment(R.layout.fragment_main) {

    private val viewModel by viewModels<ListViewModel>()

    private val adapter by lazy {
        ArticlesAdapter {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.list bindTo {
            println(it)
            tv_empty.isVisible = it.isEmpty()
            adapter.updateList(it)
        }
        viewModel.isLoading bindTo { pb_loading.isVisible = it }
    }

    private fun initRecycler() {
        rv_articles.layoutManager = LinearLayoutManager(context)
        rv_articles.adapter = adapter
        rv_articles.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}