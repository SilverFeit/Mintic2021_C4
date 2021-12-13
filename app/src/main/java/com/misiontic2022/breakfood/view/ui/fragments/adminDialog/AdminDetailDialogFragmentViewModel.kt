package com.misiontic2022.breakfood.view.ui.fragments.adminDialog

import android.util.Log
import android.view.View
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementFirebaseFireStore
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementLoginFirebase
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.UserFirebaseDTO

interface AdminDetailDialogFragmentViewModelDelegate {

}


class AdminDetailDialogFragmentViewModel constructor(
    private val delegate : AdminDetailDialogFragmentViewModelDelegate,
    private val view : View
){
    private val managementLoginFirebase = ManagementLoginFirebase.getInstance()
    private val managementFirebaseFireStore = ManagementFirebaseFireStore.getInstance()

    fun updateData(
        nombre : String,
        direccion1:String,
        direccion2:String,
        telefono:String
    ){
        Thread{
            val id = managementLoginFirebase.getCurrentfirebaseUser()?.uid?:""
            managementFirebaseFireStore.addElementTocollection(
                collection = ManagementFirebaseFireStore.CollectionsAvailables.USERS,
                id = id,
                success = {
                    Log.e("success","no surgió ningun error")
                },
                error = {
                    Log.e("error","surgió un error")
                },
                element = UserFirebaseDTO(
                    name = nombre,
                    email = managementLoginFirebase.getCurrentfirebaseUser()?.email?:"",
                    dir1 = direccion1,
                    dir2 = direccion2,
                    tel = telefono
                )
            )
        }.start()
    }

    private fun startOnMainThread(function : () ->Unit){
        view.post {
            function.invoke()
        }
    }
}