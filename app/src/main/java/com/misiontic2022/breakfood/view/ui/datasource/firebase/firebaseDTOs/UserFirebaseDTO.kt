package com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs

data class UserFirebaseDTO (
    var email : String = "",
    var name : String = "",
    var dir1 : String = "",
    var dir2 : String = "",
    var tel : String = "",


) : BaseFirebaseDTO()