package com.misiontic2022.breakfood.view.ui.fragments.loginFragment

import android.app.Activity
import android.view.View
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementFirebaseFireStore
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementLoginFirebase
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.UserFirebaseDTO

interface LoginFragmentViewModelDelegate {
    fun loginSuccess()
    fun loginFailed()
    fun signUpSuccess()
    fun signUpFailed()

}

class LoginFragmentViewModel constructor(
    private val delegate : LoginFragmentViewModelDelegate,
    private val view : View
) {

    private val managementLoginFirebase = ManagementLoginFirebase.getInstance()
    private val managementFirebaseFireStore = ManagementFirebaseFireStore.getInstance()

    fun login(activity : Activity, email: String, passwor:String) {
        managementLoginFirebase
            .signInWithUserAndPassword(
                activity= activity,
                email = email,
                password = passwor,
                success = {
                    startOnMainThread(delegate::loginSuccess)
                },
                error = {
                    startOnMainThread(delegate::loginFailed)
                }
            )
    }

    fun signUp(
        activity: Activity,
        email: String,
        passwor: String,
        name: String,
        lastName: String,
    ) {
        registerInAutienticationfirebase(
            activity = activity,
            email = email,
            passwor = passwor,
            success = {
                registerInFirestore(
                    email = email,
                    name = name,
                    success = {
                        startOnMainThread(delegate::signUpSuccess)
                    },
                    error = {
                        startOnMainThread(delegate::signUpFailed)
                    }
                )
            },
            error = {
                startOnMainThread(delegate::signUpFailed)
            }
        )
    }

    /**--------------------------------------------------
    --------------------PRIVATE METHODS------------------
    --------------------------------------------------*/

    private fun registerInAutienticationfirebase(
        activity: Activity,
        email: String,
        passwor: String,
        success: () ->Unit,
        error : () ->Unit
    ) {
        managementLoginFirebase.createUserWithUserAndPassword(
            activity = activity,
            email = email,
            password = passwor,
            success = success,
            error = error
        )
    }

    private fun registerInFirestore(
        email: String,
        name: String,
        success: () -> Unit,
        error: () -> Unit
    ){
        Thread{
            val id = managementLoginFirebase.getCurrentfirebaseUser()?.uid?:""
            managementFirebaseFireStore.addElementTocollection(
                collection = ManagementFirebaseFireStore.CollectionsAvailables.USERS,
                id = id,
                success = {
                    success.invoke()
                },
                error = {
                    error.invoke()
                },
                element = UserFirebaseDTO(
                    name = name,
                    email = email
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