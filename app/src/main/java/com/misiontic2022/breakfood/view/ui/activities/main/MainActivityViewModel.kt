package com.misiontic2022.breakfood.view.ui.activities.main

import androidx.appcompat.app.AppCompatActivity

interface MainActivityViewModelDelegate {

    fun showBottomNavBar()
    fun hiddeBottomNavBar()

}

class MainActivityViewModel constructor(
    private val mainActivity : AppCompatActivity,
    private val delegate : MainActivityViewModelDelegate
) {

    private var currentFragment = FragmentsAvailables.LOGIN

    /**--------------------------------------------------
    --------------------PUBLIC METHODS------------------
    --------------------------------------------------*/

    fun changeFragment(changeFragment: FragmentsAvailables) {
        currentFragment = changeFragment
    }

    fun checkCurrentFragment() {
        when(currentFragment) {
            FragmentsAvailables.LOGIN -> startOnMainThread(delegate::hiddeBottomNavBar)
            else -> startOnMainThread(delegate::showBottomNavBar)
        }
    }

    /**--------------------------------------------------
    --------------------PRIVATE METHODS------------------
    --------------------------------------------------*/

    private fun startOnMainThread(funcion : () -> Unit) {
        mainActivity.runOnUiThread {
            funcion.invoke()
        }
    }

    enum class FragmentsAvailables{
        LOGIN,
        OTHER,
        ;
    }
}