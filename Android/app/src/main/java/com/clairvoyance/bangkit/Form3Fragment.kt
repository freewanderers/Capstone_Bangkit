package com.clairvoyance.bangkit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.clairvoyance.bangkit.databinding.FragmentForm3Binding
import kotlinx.android.synthetic.main.fragment_form3.*
import java.lang.Exception


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

        lateinit var viewModel: MyObservable

        var Eko = 0
        var Sos = 0
        var Geo = 0
        binding.btnNext.setOnClickListener {
            Eko = input_ekonomi.text.toString().toInt()
            Sos = input_sosiologi.text.toString().toInt()
            Geo = input_geografi.text.toString().toInt()

            viewModel = activity?.run {
                ViewModelProviders.of(this).get(MyObservable::class.java)
            }?: throw Exception("Invalid Activity")

            viewModel.ekonomi.value = Eko
            viewModel.sosiologi.value = Sos
            viewModel.geografi.value = Geo

            view.findNavController().navigate(R.id.form2Fragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}