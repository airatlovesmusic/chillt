package com.airatlovesmusic.coding.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.airatlovesmusic.coding.R
import com.airatlovesmusic.coding.di.ServerModule
import com.airatlovesmusic.coding.entity.Article
import com.airatlovesmusic.coding.launchFragmentInHiltContainer
import com.airatlovesmusic.coding.model.data.ArticlesFetcher
import com.airatlovesmusic.coding.ui.list.ListFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.inject.Singleton

@UninstallModules(ServerModule::class)
@HiltAndroidTest
class ListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val articlesFetcher = mock(ArticlesFetcher::class.java)

    @Before
    fun before() {
        hiltRule.inject()
    }

    @Test
    fun testEmpty() {
        `when`(articlesFetcher.getArticles())
            .thenReturn(listOf())

        launchFragmentInHiltContainer<ListFragment>()
        onView(withId(R.id.pb_loading))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_empty))
            .check(matches(isDisplayed()))
        onView(withId(R.id.pb_loading))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun testData() {
        `when`(articlesFetcher.getArticles())
            .thenReturn((0..10).map { Article(it.toString(), it.toString()) })
        launchFragmentInHiltContainer<ListFragment>()
        onView(withId(R.id.pb_loading))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_empty))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.pb_loading))
            .check(matches(not(isDisplayed())))
    }

    @InstallIn(ApplicationComponent::class)
    @Module
    inner class ServerModule {
        @Singleton
        @Provides
        fun providesPostsFetcher(): ArticlesFetcher = articlesFetcher
    }

}