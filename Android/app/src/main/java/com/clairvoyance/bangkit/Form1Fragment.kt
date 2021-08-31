package com.clairvoyance.bangkit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.clairvoyance.bangkit.databinding.FragmentForm1Binding
import kotlinx.android.synthetic.main.fragment_form1.*
import java.lang.Exception


class Form1Fragment : Fragment() {

    private var _binding: FragmentForm1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        _binding = FragmentForm1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var viewModel: MyObservable
        var Fis = 0
        var Kim = 0
        var Bio = 0
        btn_next2.setOnClickListener {
            Fis = input_fisika.text.toString().toInt()
            Kim = input_kimia.text.toString().toInt()
            Bio = input_biologi.text.toString().toInt()

            viewModel = activity?.run {
                ViewModelProviders.of(this).get(MyObservable::class.java)
            }?: throw Exception("Invalid Activity")

            viewModel.fisika.value = Fis
            viewModel.kimia.value = Kim
            viewModel.biologi.value = Bio

            view.findNavController().navigate(R.id.form2Fragment)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}