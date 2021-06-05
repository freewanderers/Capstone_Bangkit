package com.clairvoyance.bangkit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.clairvoyance.bangkit.databinding.FragmentForm2Binding


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

        binding.btnSubmit.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_form2Fragment_to_resultFragment)
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}