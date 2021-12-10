package com.misiontic2022.breakfood.view.ui.datasource.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ManagementLoginFirebase {

    private val auth = FirebaseAuth.getInstance()

    fun createUserWithUserAndPassword(
        activity: Activity,
        email : String,
        password : String,
        success : ()->Unit,
        error : () ->Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) {
                    task ->
                if (task.isSuccessful) {
                    Log.d("Error", "createUserWithEmail:success")
                    success.invoke()
                    val user = auth.currentUser
                } else {
                    error.invoke()
                }
            }
    }

    fun signInWithUserAndPassword(
        activity: Activity,
        email : String,
        password : String,
        success : (user: FirebaseUser?)->Unit,
        error : () ->Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Error", "signInWithEmail:success")
                    val user = auth.currentUser
                    success.invoke(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Error", "signInWithEmail:failure", task.exception)
                    error.invoke()
                }
            }
    }



    companion object {
        private var instance : ManagementLoginFirebase? = null

        fun getInstance() : ManagementLoginFirebase {
            if(instance == null) {
                instance = ManagementLoginFirebase()
            }
            return instance!!
        }
    }
}