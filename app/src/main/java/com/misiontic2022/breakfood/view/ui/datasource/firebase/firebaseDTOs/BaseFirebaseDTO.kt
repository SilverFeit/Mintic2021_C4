package com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class BaseFirebaseDTO {

    //Este metodo es el encargado de convertir un objeto de BaseFirebaseDTO a un objeto json
    fun convertToHashMap() : Map<String,Any?> {
        val gson = Gson()
        val json : String = gson.toJson(this)
        val type = (object : TypeToken<HashMap<String,Any?>>(){}).type

        return gson.fromJson(json, type)
    }
}