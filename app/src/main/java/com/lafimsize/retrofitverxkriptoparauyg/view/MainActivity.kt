package com.lafimsize.retrofitverxkriptoparauyg.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lafimsize.retrofitverxkriptoparauyg.databinding.ActivityMainBinding
import com.lafimsize.retrofitverxkriptoparauyg.model.CryptoModel
import com.lafimsize.retrofitverxkriptoparauyg.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cryptoModels: ArrayList<CryptoModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //url=https://raw.githubusercontent.com/
        //atilsamancioglu/K21-JSONDataSet/master/crypto.json

        loadData()

    }

    fun loadData(){

        val retrofit=Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service=retrofit.create(CryptoAPI::class.java)

        val call=service.getData()

        call.enqueue(object : Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels=ArrayList(it)
                        for (cryptoModel in cryptoModels){
                            println(cryptoModel.currency+"==="+cryptoModel.price)

                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        }
}