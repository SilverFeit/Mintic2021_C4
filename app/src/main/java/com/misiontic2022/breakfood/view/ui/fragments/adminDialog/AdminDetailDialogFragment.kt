package com.misiontic2022.breakfood.view.ui.fragments.adminDialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.databinding.FragmentAdminDetailDialogBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminDetailDialogFragment : Fragment(), AdminDetailDialogFragmentViewModelDelegate{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel : AdminDetailDialogFragmentViewModel
    private lateinit var binding: FragmentAdminDetailDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminDetailDialogBinding.inflate(layoutInflater)
        addListeners()
        viewModel = AdminDetailDialogFragmentViewModel(delegate = this, view = binding.root)
        return binding.root
    }


    /**--------------------------------------------------
    --------------------PRIVATE METHODS------------------
    --------------------------------------------------*/

    private fun addListeners(){
        addListenerUpdate()
    }

    private fun addListenerUpdate(){
        binding.btSaveAdmin.setOnClickListener({
            viewModel.updateData(
                nombre = binding.etNameAdmin.text.toString(),
                direccion1 = binding.etAddressAdmin.text.toString(),
                direccion2 = binding.etAddress2Admin.text.toString(),
                telefono = binding.etPhoneAdmin.text.toString()
            )
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdminDetailDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdminDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}