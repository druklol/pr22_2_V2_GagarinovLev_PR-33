package com.example.pr22_2_v2_gagarinovlev_pr33.registration

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pr22_2_v2_gagarinovlev_pr33.R
import com.example.pr22_2_v2_gagarinovlev_pr33.databinding.FragmentLoginBinding
import kotlin.math.log

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    val APP_PREFERENCES = "logins"
    val PREFERENCES_LOGIN = "Login"
    val PREFERENCES_PASSWORD = "Password"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        binding.apply {
            loginBtn.setOnClickListener{
                val login = loginEd.text.toString()
                val password = passwordEd.text.toString()
                if(password == "" || login=="")
                {
                    Toast.makeText(requireContext(),getString(R.string.error_empty_fields), Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val msettings = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

                val savedPassword=msettings.getString(PREFERENCES_PASSWORD,"")
                val savedLogin=msettings.getString(PREFERENCES_LOGIN,"")

                if(password!=savedPassword || login!=savedLogin){
                    Toast.makeText(requireContext(),getString(R.string.error_not_correct_fields), Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                navigateToPokemonsFragment()


            }

            registrationBtn.setOnClickListener{
                navigateToRegistrationFragment()
            }
        }


        return binding.root
    }

    private fun navigateToRegistrationFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
    }

    private fun navigateToPokemonsFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_pokemons_fragment)
    }
}