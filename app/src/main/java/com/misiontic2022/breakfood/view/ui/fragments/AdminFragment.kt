package com.misiontic2022.breakfood.view.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.databinding.FragmentAdminBinding
import com.misiontic2022.breakfood.view.ui.fragments.adminFragment.AdminFragmentViewModel
import com.misiontic2022.breakfood.view.ui.fragments.adminFragment.AdminFragmentViewModelDelegate

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
    private val viewModel = AdminFragmentViewModel(this)

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
        addListeners()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun addListeners() {
        binding.tvIniciarSesion.setOnClickListener {
            viewModel.login(
                activity = context as Activity,
                email = binding.edNameAdmin.text.toString(),
                passwor = binding.edPassword.text.toString()
            )
        }
    }

    override fun loginSuccess() {
        binding.tvIniciarSesion.post {
            Toast.makeText(context,"Me he logeado",Toast.LENGTH_LONG).show()
        }
    }

    override fun loginFailed() {
        binding.tvIniciarSesion.post {
            Toast.makeText(context,"Me he logeado",Toast.LENGTH_LONG).show()
        }
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