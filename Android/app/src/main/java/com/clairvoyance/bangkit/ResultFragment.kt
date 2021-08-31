package com.clairvoyance.bangkit

import android.opengl.EGL14
import android.opengl.GLES30
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_result.*
import okhttp3.*
import java.io.IOException
open class MyObservable: ViewModel(){
    val indo = MutableLiveData<Int>()
    val inggris = MutableLiveData<Int>()
    val matematika = MutableLiveData<Int>()
    val fisika = MutableLiveData<Int>()
    val kimia = MutableLiveData<Int>()
    val biologi = MutableLiveData<Int>()
    val ekonomi = MutableLiveData<Int>()
    val sosiologi = MutableLiveData<Int>()
    val geografi = MutableLiveData<Int>()

    fun indo(item: Int){
        indo.value = 0
        indo.value = item
    }

    fun inggris(item:Int){
        inggris.value = 0
        inggris.value = item
    }

    fun matematika(item: Int){
        matematika.value = 0
        matematika.value = item
    }
    fun fisika(item: Int){
        fisika.value = 0
        fisika.value = item
    }
    fun kimia(item: Int){
        kimia.value = 0
        kimia.value = item
    }
    fun biologi(item: Int){
        biologi.value = 0
        biologi.value = item
    }
    fun ekonomi(item: Int){
        ekonomi.value = 0
        ekonomi.value = item
    }
    fun sosiologi(item: Int){
        sosiologi.value = 0
        sosiologi.value = item
    }
    fun geografi(item: Int){
        geografi.value = 0
        geografi.value = item
    }
}

class ResultFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var viewModel: MyObservable

        var Fisika = 0
        var Kimia = 0
        var Biologi = 0
        var Indonesia = 0
        var Inggris = 0
        var Matematika = 0
        var Ekonomi = 0
        var Geografi = 0
        var Sosiologi = 0

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(MyObservable::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel.indo.observe(viewLifecycleOwner, Observer {
            Inggris = viewModel.inggris.value!!
            Matematika = viewModel.matematika.value!!
            Indonesia = viewModel.indo.value!!
            try {
                Fisika = viewModel.fisika.value!!
                Kimia = viewModel.kimia.value!!
                Biologi = viewModel.biologi.value!!
            }catch (e: Exception) {
                Ekonomi = viewModel.ekonomi.value!!
                Geografi = viewModel.geografi.value!!
                Sosiologi = viewModel.sosiologi.value!!
            }


            Log.e("Fisika", Fisika.toString())
            Log.e("Kimia", Kimia.toString())
            Log.e("Biologi", Biologi.toString())
            Log.e("Matematika", Matematika.toString())
            Log.e("Inggris", Inggris.toString())
            Log.e("Indonesia", Indonesia.toString())
            Log.e("Ekonomi", Ekonomi.toString())
            Log.e("Sosiologi", Sosiologi.toString())
            Log.e("Geografi", Geografi.toString())

            val url = "https://capstone-bakat.et.r.appspot.com/api?Ind=" + Indonesia +"&Ing=" + Inggris + "&Mat=" + Matematika + "&Kim=" + Kimia + "&Fis=" + Fisika + "&Bio=" + Biologi + "&Eko=" + Ekonomi + "&Geo=" + Geografi + "&Sos=" + Sosiologi

            Log.e("url", url)

            val request = Request.Builder()
//                .url("https://capstone-bakat.et.r.appspot.com/api?Ind=80&Ing=77&Mat=80&Kim=0&Fis=0&Bio=0&Eko=66&Geo=77&Sos=89")
                .url(url)
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

                    activity!!.runOnUiThread(Runnable {
                        var result2 = gson.fromJson(body, Placeholder::class.java)
                        txResult2.text = result2?.Universitas?.get(0).toString()
                        txResult3.text = result2?.Universitas?.get(1).toString()
                        txResult4.text = result2?.Universitas?.get(2).toString()
                        txResult5.text = result2?.Universitas?.get(3).toString()
                        txResult6.text = result2?.Universitas?.get(4).toString()
                    })


//                    var res =  gson.fromJson(body, Placeholder::class.java)
//                    Log.e("eee", res.Universitas.get(0).toString())

                }
            })
        })

    }

}