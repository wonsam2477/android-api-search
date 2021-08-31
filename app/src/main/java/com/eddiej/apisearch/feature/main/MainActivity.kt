package com.eddiej.apisearch.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.eddiej.apisearch.R
import com.eddiej.apisearch.databinding.ActivityMainBinding
import com.eddiej.apisearch.feature.BaseActivity
import com.eddiej.apisearch.global.ProjectId
import com.eddiej.apisearch.global.ProjectLayout

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frag = supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        navController = frag.navController
//        appBarConfiguration =
//            AppBarConfiguration.Builder(ProjectId.bookFragment)
//                .build()

        setupActionBarWithNavController(navController)
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.activity_main
}