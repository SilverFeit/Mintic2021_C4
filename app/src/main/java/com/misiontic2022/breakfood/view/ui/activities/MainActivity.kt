package com.misiontic2022.breakfood.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.databinding.ActivityMainBinding
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementFirebaseFireStore
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementLoginFirebase
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.UserFirebaseDTO

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    private val managementFirebaseFirestore = ManagementFirebaseFireStore.getInstance()
    private val managementLoginFirebase = ManagementLoginFirebase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread{
            managementLoginFirebase.createUserWithUserAndPassword(
                activity = this,
                email = "jj@jj.com",
                password = "112233",
                success = {
                    runOnUiThread {
                        Toast.makeText(this, "Me he registrado",Toast.LENGTH_LONG).show()
                    }
                },
                error = {
                    runOnUiThread {
                        Toast.makeText(this, "Ya esta registrado",Toast.LENGTH_LONG).show()
                    }
                }
            )
            /*
            //TODO : aqui leen una coleccion
            managementFirebaseFirestore.getAllElements(
                collection = ManagementFirebaseFireStore.CollectionsAvailables.USERS,
                classe = UserFirebaseDTO::class.java,
                succes = {
                    listUsers ->
                    val currentList = listUsers as List<UserFirebaseDTO>
                    Log.e("Error", "Ha llegado la consulta")
                },
                error = {
                    Log.e("Error", "Fallo la consulta")
                }
            )
            */
            /*
            //TODo: aqui adicionan un elemento a una coleccion
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
            */
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
