package com.misiontic2022.breakfood.view.ui.base

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class CurrentApplication : Application() {

    override fun onCreate() {
        Firebase.initialize(this)
        super.onCreate()
    }
}