package com.misiontic2022.breakfood.view.ui.datasource.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ManagementLoginFirebase private constructor() {

    private val auth = FirebaseAuth.getInstance()
    private var firebaseUSer : FirebaseUser? = null

    fun getCurrentfirebaseUser() = firebaseUSer

    fun createUserWithUserAndPassword(
        activity: Activity,
        email : String,
        password : String,
        success : ()->Unit,
        error : () ->Unit
    ) {
        if (isInvalidLoginAndPassword(email, password)) {
            error.invoke()
            return
        }
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) {
                        task ->
                    if (task.isSuccessful) {
                        Log.d("Error", "createUserWithEmail:success")
                        success.invoke()
                        firebaseUSer = auth.currentUser
                    } else {
                        error.invoke()
                    }
                }
        } catch (e: Exception) {
            Log.e("Error", "Surgio un error", e)
            error.invoke()
        }
    }

    fun signInWithUserAndPassword(
        activity: Activity,
        email : String,
        password : String,
        success : (user: FirebaseUser?)->Unit,
        error : () ->Unit
    ) {
        if (isInvalidLoginAndPassword(email, password)) {
            error.invoke()
            return
        }

        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Error", "signInWithEmail:success")
                        val user = auth.currentUser
                        firebaseUSer = user
                        success.invoke(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Error", "signInWithEmail:failure", task.exception)
                        error.invoke()
                    }
                }
        } catch (e: Exception) {
            Log.e("Error", "Fallo", e)
            error.invoke()
        }
    }

    private fun isInvalidLoginAndPassword(email: String, password: String) : Boolean {
        return email.isNullOrEmpty() || password.isNullOrEmpty()
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