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

    companion object {

        fun <T: BaseFirebaseDTO> convertToDTO(objec :Any, classe : Class<T>) : T {
            val gson = Gson()
            val json = gson.toJson(objec)
            return gson.fromJson(json , classe)
        }

        fun <T: BaseFirebaseDTO> convertToListDTO(objecs :List<Any>, classe : Class<T>) : List<T> {
            val listResult = arrayListOf<T>()
            for (currentObject in objecs) {
                listResult.add(convertToDTO(currentObject, classe))
            }
            return listResult
        }

    }
}
