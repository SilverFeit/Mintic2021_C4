package com.misiontic2022.breakfood.view.ui.fragments.loginFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.databinding.FragmentLoginBinding
import com.misiontic2022.breakfood.view.ui.activities.main.MainActivityViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), LoginFragmentViewModelDelegate {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel : LoginFragmentViewModel
    private lateinit var binding : FragmentLoginBinding

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
        binding = FragmentLoginBinding.inflate(layoutInflater)
        addListeners()
        viewModel = LoginFragmentViewModel(delegate = this, view = binding.root)
        return binding.root
    }


    /**--------------------------------------------------
    --------------------PRIVATE METHODS------------------
    --------------------------------------------------*/

    private fun addListeners() {
        addListenerLogin()
        addListenerSignUp()
    }

    private fun addListenerLogin() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                activity = context as Activity,
                email = binding.edNameAdmin.text.toString(),
                passwor = binding.edPassword.text.toString()
            )
        }
    }

    private fun addListenerSignUp() {
        binding.btnSignup.setOnClickListener {
            viewModel.signUp(
                activity = context as Activity,
                email = binding.edNameAdmin.text.toString(),
                passwor = binding.edPassword.text.toString(),
                name = " ",
                lastName = " "
            )
        }
    }

    /**
     * Delegates
     */

    override fun loginSuccess() = Navigation.findNavController(binding.root).navigate(MainActivityViewModel.FragmentsAvailables.HOME.getCurrentFragment())

    override fun loginFailed() = Toast.makeText(context,"Revisa tus credenciales", Toast.LENGTH_LONG).show()

    override fun signUpSuccess() = Toast.makeText(context,"Se ha registrado con exito", Toast.LENGTH_LONG).show()

    override fun signUpFailed() = Toast.makeText(context,"El usuario ya existe", Toast.LENGTH_LONG).show()


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}