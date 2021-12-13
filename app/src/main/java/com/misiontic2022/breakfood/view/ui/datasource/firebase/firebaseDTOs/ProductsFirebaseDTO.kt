package com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs

data class ProductsFirebaseDTO (
    var description : String = "",
    var name : String = "",
    var price : String = "",
    var stock : String = "",
    var url : String = "",


) : BaseFirebaseDTO()