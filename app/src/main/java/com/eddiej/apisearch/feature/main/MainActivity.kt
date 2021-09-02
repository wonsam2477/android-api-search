package com.eddiej.apisearch.feature.main

import android.os.Bundle
import com.eddiej.apisearch.databinding.ActivityMainBinding
import com.eddiej.apisearch.feature.BaseActivity
import com.eddiej.apisearch.feature.book.BookFragment
import com.eddiej.apisearch.global.ProjectLayout
import com.eddiej.apisearch.util.FragmentUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = BookFragment.newInstance()
        FragmentUtils.replaceFragmentNoBackstack(supportFragmentManager, fragment, binding.container.id)
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.activity_main
}