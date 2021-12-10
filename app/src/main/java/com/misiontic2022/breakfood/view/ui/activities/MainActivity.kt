package com.misiontic2022.breakfood.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.databinding.ActivityMainBinding
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementFirebaseFireStore
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.UserFirebaseDTO

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    private val managementFirebaseFirestore = ManagementFirebaseFireStore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread{
            managementFirebaseFirestore.addElementTocollection(
                collection = ManagementFirebaseFireStore.CollectionsAvailables.USERS,
                element = UserFirebaseDTO(
                    name = "Loco",
                    lastName = "Daniel"
                ),
                success = {
                    Log.e("Error", "Se guardo bien")
                },
                error = {
                    currentError ->
                    Log.e("Error", currentError)
                }
            )
        }.start()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configNav()
    }

    fun configNav(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bnvMenu).setupWithNavController(navController)
    }
}