package com.misiontic2022.breakfood.view.ui.fragments.loginFragment

import android.app.Activity
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementLoginFirebase

interface LoginFragmentViewModelDelegate {

    fun loginSuccess()
    fun loginFailed()
}

class LoginFragmentViewModel constructor(
    private val delegate : LoginFragmentViewModelDelegate
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