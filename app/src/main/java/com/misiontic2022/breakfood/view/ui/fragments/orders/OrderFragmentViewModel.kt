package com.misiontic2022.breakfood.view.ui.fragments.orders

import android.view.View
import com.misiontic2022.breakfood.view.ui.datasource.firebase.ManagementFirebaseFireStore
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.ProductsFirebaseDTO

interface OrderFragmentViewModelDelegate {
    fun loadedData(list : List<ProductsFirebaseDTO>)
    fun showErrorLoadData()
}

class OrderFragmentViewModel constructor(

    private val delegate: OrderFragmentViewModelDelegate,
    private val view: View

) {

    private val managementFirebaseFireStore = ManagementFirebaseFireStore.getInstance()

    fun LoadProducts() {
        managementFirebaseFireStore.getAllElements(
            collection = ManagementFirebaseFireStore.CollectionsAvailables.PRODUCTS,
            classe = ProductsFirebaseDTO::class.java,
            succes = {
                startInMainThread {
                    delegate.loadedData(it)
                }
            },
            error = {
                startInMainThread {
                    delegate.showErrorLoadData()
                }
            }
        )
    }

    private fun startInMainThread(function: () -> Unit) {
        view.post {
            function.invoke()
        }
    }
}