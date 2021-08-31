package com.clairvoyance.bangkit

import android.opengl.EGL14
import android.opengl.GLES30
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_result.*
import okhttp3.*
import java.io.IOException


class ResultFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }
// Send request via OkHttp module to API
    fun getData() {
        val url = ""

        val request = Request.Builder()
            .url("https://capstone-bakat.et.r.appspot.com/api?Ind=80&Ing=77&Mat=80&Kim=0&Fis=0&Bio=0&Eko=66&Geo=77&Sos=89")
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Failed", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                var gson = GsonBuilder().create()
                var result = gson.fromJson(body, Placeholder::class.java)
                txResult.text = result?.jurusan.toString()

            }

        })
    }
}