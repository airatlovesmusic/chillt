package com.airatlovesmusic.coding.di

import com.airatlovesmusic.coding.model.data.ArticlesFetcher
import com.airatlovesmusic.coding.model.data.ArticlesFetcherImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServerModule {

    @Singleton
    @Provides
    fun provideArticlesFetcher(): ArticlesFetcher = ArticlesFetcherImpl()

}