package com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs

import com.google.gson.annotations.SerializedName

data class ProductsFirebaseDTO(
    @SerializedName("DESCRIPTION") var description: String = "",
    @SerializedName("NAME") var name: String = "",
    @SerializedName("PRICE") var price: String = "",
    @SerializedName("STOCK") var stock: String = "",
    @SerializedName("URL") var url: String = "",

    ) : BaseFirebaseDTO()