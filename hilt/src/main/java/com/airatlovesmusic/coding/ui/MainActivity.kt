package com.airatlovesmusic.coding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airatlovesmusic.coding.R
import com.airatlovesmusic.coding.ui.list.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_content,
                    ListFragment()
                )
                .commit()
        }
    }

}