package com.misiontic2022.breakfood.view.ui.fragments.admin

import android.view.View
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementFirebaseFireStore
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementLoginFirebase
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.UserFirebaseDTO

interface AdminFragmentViewModelDelegate {
    fun updateUser(user : UserFirebaseDTO)
    fun showError(errod : String)
}

class AdminFragmentViewModel constructor (
    private val delegate: AdminFragmentViewModelDelegate,
    private val view: View
){

    private val managementLoginFirebase = ManagementLoginFirebase.getInstance()
    private val managementFirebaseFireStore = ManagementFirebaseFireStore.getInstance()

    fun loadDetailUser() {
        Thread {
            managementFirebaseFireStore.getElement(
                collection = ManagementFirebaseFireStore.CollectionsAvailables.USERS,
                id = managementLoginFirebase.getCurrentfirebaseUser()?.uid?:"",
                classe = UserFirebaseDTO::class.java,
                succes = {
                    currentUser ->
                    startInMainThread{
                        delegate.updateUser(user = currentUser)
                    }
                },
                error = {
                    startInMainThread {
                        delegate.showError(it)
                    }
                }
            )
        }.start()
    }

    private fun startInMainThread(function : ()->Unit) {
        view.post {
            function.invoke()
        }
    }
}