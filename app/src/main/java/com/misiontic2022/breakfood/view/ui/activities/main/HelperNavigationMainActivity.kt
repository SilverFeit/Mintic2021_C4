package com.misiontic2022.breakfood.view.ui.activities.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.misiontic2022.breakfood.R

class HelperNavigationMainActivity constructor(
    private val supportFragmentManager : FragmentManager,
    private val mainActivityViewModel: MainActivityViewModel
) : NavController.OnDestinationChangedListener {
    private val navHostFragment : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
    private val navController : NavController = navHostFragment.navController



    /**--------------------------------------------------
    --------------------PUBLIC METHODS------------------
    --------------------------------------------------*/
    fun addListenerNavController() {
        navController.addOnDestinationChangedListener(this)
    }

    fun removeListenerNavController() {
        navController.removeOnDestinationChangedListener(this)
    }

    /**--------------------------------------------------
    --------------------PRIVATE METHODS------------------
    --------------------------------------------------*/



    /**--------------------------------------------------
    --------------------DELEGATE METHODS------------------
    --------------------------------------------------*/
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val currentFragment = MainActivityViewModel.FragmentsAvailables.findFragmentById(id = controller.currentDestination?.id)
        mainActivityViewModel.changeFragment(changeFragment = currentFragment)
        mainActivityViewModel.checkCurrentFragment()
    }
}