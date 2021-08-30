package com.clairvoyance.bangkit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.clairvoyance.bangkit.databinding.FragmentForm3Binding
import kotlinx.android.synthetic.main.fragment_form3.*


class Form3Fragment : Fragment() {

    private var _binding: FragmentForm3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentForm3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener{
            val Eko = input_ekonomi.text.toString().toInt()
            val Sos = input_sosiologi.text.toString().toInt()
            val Geo = input_geografi.text.toString().toInt()
            view.findNavController().navigate(R.id.form2Fragment)
        }


//        binding.btnNext.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.action_form3Fragment_to_form2Fragment)
//        )

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}