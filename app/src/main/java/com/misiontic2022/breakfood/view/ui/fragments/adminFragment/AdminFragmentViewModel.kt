package com.misiontic2022.breakfood.view.ui.fragments.adminFragment

import android.app.Activity
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementLoginFirebase

interface AdminFragmentViewModelDelegate {

    fun loginSuccess()
    fun loginFailed()
}

class AdminFragmentViewModel constructor(
    private val delegate : AdminFragmentViewModelDelegate
) {

    private val managementLoginFirebase = ManagementLoginFirebase.getInstance()

    fun login(activity : Activity, email: String, passwor:String) {
        managementLoginFirebase
            .signInWithUserAndPassword(
                activity= activity,
                email = email,
                password = passwor,
                success = {
                    delegate.loginSuccess()
                },
                error = {
                    delegate.loginFailed()
                }
            )
    }

}