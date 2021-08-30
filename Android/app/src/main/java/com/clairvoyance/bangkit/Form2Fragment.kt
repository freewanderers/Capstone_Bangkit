package com.clairvoyance.bangkit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.clairvoyance.bangkit.databinding.FragmentForm2Binding
import kotlinx.android.synthetic.main.fragment_form2.*


class Form2Fragment : Fragment() {

    private var _binding: FragmentForm2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentForm2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            val Ind = input_bahasa_indo.text.toString().toInt()
            val Ing = input_bahasa_inggris.text.toString().toInt()
            val Mtk = input_mtk.text.toString().toInt()
            Log.e("mat", Mtk.toString())
            Log.e("Ing", Ing.toString())
            Log.e("ind", Ind.toString())

            view.findNavController().navigate(R.id.resultFragment)
//            Navigation.createNavigateOnClickListener(R.id.action_form2Fragment_to_resultFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}