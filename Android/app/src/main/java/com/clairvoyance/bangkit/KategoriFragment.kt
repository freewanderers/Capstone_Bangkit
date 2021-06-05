package com.clairvoyance.bangkit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.clairvoyance.bangkit.databinding.FragmentKategoriBinding


class KategoriFragment : Fragment() {
    private var _binding: FragmentKategoriBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIpa.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_kategoriFragment_to_form1Fragment)
        )

        binding.btnIps.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_kategoriFragment_to_form3Fragment)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}