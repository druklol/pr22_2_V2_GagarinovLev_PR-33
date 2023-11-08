package com.example.pr22_2_v2_gagarinovlev_pr33.registration

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pr22_2_v2_gagarinovlev_pr33.R
import com.example.pr22_2_v2_gagarinovlev_pr33.databinding.FragmentLoginBinding
import com.example.pr22_2_v2_gagarinovlev_pr33.databinding.FragmentPokemonsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class Pokemons_fragment : Fragment() {

    private  lateinit var binding:FragmentPokemonsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentPokemonsFragmentBinding.inflate(layoutInflater, container,false)

        binding.apply {
            getInfoBtn.setOnClickListener {
                val id = pokemonIdEd.text.toString()
                getInfoAboutPokemon(id)
            }
        }

        return binding.root
    }

    private fun getInfoAboutPokemon(id:String){
        if(id.isEmpty())
        {
            Snackbar.make(requireActivity().findViewById(android.R.id.content) , R.string.error_empty_fields, Snackbar.LENGTH_LONG).setActionTextColor(
                Color.RED).show()
            return
        }

        val url= "https://pokeapi.co/api/v2/pokemon/${id}"
        val queue = Volley.newRequestQueue(requireContext())
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                    response->
                binding.apply {
                    val obj = JSONObject(response)

                    val pokemonId=obj.getString("id")
                    pokemonIdTw.text = "ID покемона: $pokemonId"

                    val pokemonName= obj.getString("name")
                    pokemonNameTw.text="Имя покемона: $pokemonName"

                    val pokemonHeight = obj.getString("height")
                    pokemonHeightTw.text="Рост покемона: $pokemonHeight"


                    val abilitiesArray = obj.getJSONArray("abilities")
                    var abilitiesString = "Способности: "
                    for (i in 0 until abilitiesArray.length()){
                        val currentAbility=abilitiesArray.getJSONObject(i).getJSONObject("ability").getString("name")
                        abilitiesString+="${currentAbility}, "
                    }
                    abilitiesString=abilitiesString.removeRange(abilitiesString.length-2,abilitiesString.length-1)
                    pokemonAbilitiesTw.text=abilitiesString
                }


            }
        ) {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                R.string.error_unknown_pokemon,
                Snackbar.LENGTH_LONG
            ).setActionTextColor(
                Color.RED
            ).show()
        }

        queue.add(stringRequest)
    }

}