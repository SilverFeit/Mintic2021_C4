package com.misiontic2022.breakfood.view.ui.fragments.admin

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.databinding.FragmentAdminBinding
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.UserFirebaseDTO
import com.misiontic2022.breakfood.view.ui.fragments.loginFragment.LoginFragmentViewModel
import com.misiontic2022.breakfood.view.ui.fragments.loginFragment.LoginFragmentViewModelDelegate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment(), AdminFragmentViewModelDelegate {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var binding : FragmentAdminBinding
    private lateinit var viewModel : AdminFragmentViewModel

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
        binding = FragmentAdminBinding.inflate(layoutInflater)
        viewModel = AdminFragmentViewModel(delegate = this, view = binding.root)
        viewModel.loadDetailUser()
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.navigate_edition)
        button?.setOnClickListener {
            findNavController().navigate(R.id.adminDetailFragmentDialog, null)
        }
    }

    override fun updateUser(user: UserFirebaseDTO) {
        binding.tvNameAdmin.setText(user.name)
        binding.tvDir1Admin.setText(user.dir1)
        binding.tvDir2Admin.setText(user.dir2)
        binding.tvNumAdmin.setText(user.tel)
    }

    override fun showError(errod: String) {
        Toast.makeText(context, "Surguio un problema al cargar la data", Toast.LENGTH_LONG).show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdminFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdminFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}