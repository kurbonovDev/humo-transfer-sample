package com.example.humo_transfer_sample.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.adapters.CountryAdapter
import com.example.humo_transfer_sample.models.CountryItem
import com.example.humo_transfer_sample.databinding.ActivityMainBinding
import com.example.humo_transfer_sample.utils.ExitDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rcView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private var popularCountryList = listOf<CountryItem>(
        CountryItem("Таджикистан", R.drawable.tj,1.0,"Таджикистан","TJS"),
        CountryItem("Россия", R.drawable.ru,0.1172,"Россию","RUB"),
        CountryItem("Узбекистан", R.drawable.uzb,0.0009,"Узбекистан","UZS")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences("my_storage",Context.MODE_PRIVATE)


    }

    override fun onResume() {
        super.onResume()
        initRcView()

        binding.btnSend.setOnClickListener {
            startActivity(Intent(this,ChooseCountryActivity::class.java))
        }

        binding.cardView2.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=tj.humo.transfer"))
            )
        }

        binding.exitBlock.setOnClickListener {
            ExitDialog.showDialog(this,object :ExitDialog.Listener{
                override fun onClick() {

                    sharedPreferences.edit().clear().apply()

                    startActivity(Intent(this@MainActivity,LoginActivity::class.java))
                    finish()

                }
            })
        }
    }

    private fun initRcView() {
        rcView = binding.rcView
        rcView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = CountryAdapter(popularCountryList,true)
        rcView.adapter = adapter
    }
}