package com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs

data class UserFirebaseDTO (
    var name : String = "",
    var lastName : String = ""
) : BaseFirebaseDTO()