package com.example.pr22_2_v2_gagarinovlev_pr33.registration

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pr22_2_v2_gagarinovlev_pr33.R
import com.example.pr22_2_v2_gagarinovlev_pr33.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    val APP_PREFERENCES = "logins"
    val PREFERENCES_LOGIN = "Login"
    val PREFERENCES_PASSWORD = "Password"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentRegistrationBinding.inflate(layoutInflater,container,false)

        binding.apply {
            registrationBtn.setOnClickListener{
                val login = loginEd.text.toString()
                val password = passwordEd.text.toString()
                if(password == "" || login=="")
                {
                    Toast.makeText(requireContext(),getString(R.string.error_empty_fields),Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val msettings = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                val editor = msettings.edit()
                editor.putString(PREFERENCES_LOGIN, login)
                editor.putString(PREFERENCES_PASSWORD, password)
                editor.apply()

                Toast.makeText(requireContext(),getString(R.string.succesful_registration),Toast.LENGTH_LONG).show()
                navigateToLoginFragment()
            }
        }


        return binding.root
    }

    private fun navigateToLoginFragment(){
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }
}