package com.misiontic2022.breakfood.view.ui.activities.main

import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.misiontic2022.breakfood.R

class HelperNavigationMainActivity constructor(
    private val supportFragmentManager : FragmentManager
) {
    private val navHostFragment : NavHostFragment
    private val navController : Nav= navHostFragment.navController

    init {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
    }
}