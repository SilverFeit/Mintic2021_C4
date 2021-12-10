package com.misiontic2022.breakfood.view.ui.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.BaseFirebaseDTO

class ManagementFirebaseFireStore private  constructor() {
//Se crea la instancia de firebase
     private val db : FirebaseFirestore = Firebase.firestore

    /**
     * public methods
     */
    // Es un metodo generico que recibe una coleccion de tipo enum del que se obtendra el nombre para insertar la data
    // en la coleccion de firebase
    // resibe un elemento que obligatoriamente extiende de BaseFirebaseDTO la cual se guardara en firebas
    // recibe dos funiones que seran ejecutadas dependiendo del caso si se inserto correctamento o surgio un error
    // durante la insercion del documento
    fun <T: BaseFirebaseDTO> addElementTocollection(
        collection : CollectionsAvailables,
        element : T ,
        success: ((T)-> Unit),
        error: ((String)-> Unit),
    ) {
        db
            .collection(collection.name)
            .add(element.convertToHashMap())
            .addOnSuccessListener {
                success.invoke(element)
            }
            .addOnFailureListener {
                error("Fallo la insercion del elemento")
                Log.e("Error", "Surgio un error", it)
            }
    }


    /**
     * Private methods
     */

    companion object {
        private var instance : ManagementFirebaseFireStore? = null

        fun getInstance() : ManagementFirebaseFireStore {
            if(instance == null) {
                instance = ManagementFirebaseFireStore()
            }
            return instance!!
        }
    }

    enum class CollectionsAvailables (private val nameC : String){
        USERS("Users");
        fun getCollectionName() : String = nameC
    }
}