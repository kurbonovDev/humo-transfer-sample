package com.example.humo_transfer_sample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.adapters.CountryAdapter
import com.example.humo_transfer_sample.models.CountryItem
import com.example.humo_transfer_sample.databinding.ActivityChooseCountryBinding
import com.example.humo_transfer_sample.utils.RoundedItemDecorator
import java.util.Locale

class ChooseCountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseCountryBinding

    private lateinit var rcView: RecyclerView
    private lateinit var adapter: CountryAdapter

    private var countries = listOf<CountryItem>(
        CountryItem("Таджикистан", R.drawable.tj,1.0,"Таджикистан","TJS"),
        CountryItem("Россия", R.drawable.ru,0.1172,"Россию","RUB"),
        CountryItem("Узбекистан", R.drawable.uzb,0.0009,"Узбекистан","UZB"),
        CountryItem("Казахстан",R.drawable.kz,0.0245,"Казахстан","KZT"),
        CountryItem("ОАЭ",R.drawable.arab,0.34,"ОАЭ","AED"),
        CountryItem("Корея",R.drawable.korea,125.99,"Корею","KRW"),
        CountryItem("Украина",R.drawable.ukraine,3.6393,"Украину","UAH"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityChooseCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()

        initRcView()

        binding.back.setOnClickListener {
           onBackPressed()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {

        if (query!=null){
            val filteredList =ArrayList<CountryItem>()
            for (i in countries){
                if (i.nameCountry.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()){
                Toast.makeText(this,"Не нашлось такой страны !", Toast.LENGTH_SHORT).show()
                adapter.setFilterList(filteredList)
            }else{
                adapter.setFilterList(filteredList)
            }
        }
    }


    private fun initRcView(){
        rcView=binding.rcView
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter(countries)
        rcView.adapter=adapter
        val radiusInDp = 16f
        val roundedItemDecorator = RoundedItemDecorator(radiusInDp, this)
        rcView.addItemDecoration(roundedItemDecorator)
    }

}