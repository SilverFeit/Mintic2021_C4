package com.misiontic2022.breakfood.view.ui.activities.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainActivityViewModelDelegate {

    private lateinit var binding: ActivityMainBinding
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1

    private lateinit var helperNavigationMainActivity: HelperNavigationMainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        helperNavigationMainActivity = HelperNavigationMainActivity(supportFragmentManager)
        val view = binding.root
        setContentView(view)
        configNav()
        configListenerCurrentNav()
    }

    fun configNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bnvMenu).setupWithNavController(navController)
    }

    /**--------------------------------------------------
    --------------------PRIVATE METHODS------------------
    --------------------------------------------------*/

    private fun configListenerCurrentNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        val id = navController.currentDestination?.id
        if (id == R.id.loginFragment) {

        }
    }

    /**--------------------------------------------------
    --------------------DELEGATE METHODS------------------
    --------------------------------------------------*/
    override fun showBottomNavBar() {
        binding.bnvMenu.visibility = View.VISIBLE
    }

    override fun hiddeBottomNavBar() {
        binding.bnvMenu.visibility = View.GONE
    }


}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    val permissionsToRequest = ArrayList<String>();
    var i = 0;
    while (i < grantResults.size) {
        permissionsToRequest.add(permissions[i]);
        i++;
    }
    if (permissionsToRequest.size > 0) {
        ActivityCompat.requestPermissions(
            this,
            permissionsToRequest.toTypedArray(),
            REQUEST_PERMISSIONS_REQUEST_CODE
        );
    }
}
}