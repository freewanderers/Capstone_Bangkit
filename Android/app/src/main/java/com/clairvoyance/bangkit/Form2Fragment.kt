package com.clairvoyance.bangkit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.clairvoyance.bangkit.databinding.FragmentForm2Binding
import kotlinx.android.synthetic.main.fragment_form2.*
import java.lang.Exception


class Form2Fragment : Fragment() {

    private var _binding: FragmentForm2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentForm2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var viewModel: MyObservable

        var Mtk = 0
        var Ind = 0
        var Ing = 0
        binding.btnSubmit.setOnClickListener {
            Ind = input_bahasa_indo.text.toString().toInt()
            Ing = input_bahasa_inggris.text.toString().toInt()
            Mtk = input_mtk.text.toString().toInt()
//            Log.e("mat", Mtk.toString())
//            Log.e("Ing", Ing.toString())
//            Log.e("ind",Ind.toString())

            viewModel = activity?.run {
                ViewModelProviders.of(this).get(MyObservable::class.java)
            } ?: throw Exception("Invalid Activity")

            viewModel.indo.value = Ind
            viewModel.inggris.value = Ing
            viewModel.matematika.value = Mtk

            view.findNavController().navigate(R.id.resultFragment)
        }
    }
}