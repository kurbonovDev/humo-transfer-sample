package com.example.humo_transfer_sample.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.humo_transfer_sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private  var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences=getSharedPreferences("my_storage",Context.MODE_PRIVATE)

        if (sharedPreferences?.contains("phone_number") == true){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }


    override fun onResume() {
        super.onResume()
        isAgreeAndIsNumberCorrect()
        binding.nextPage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            sharedPreferences?.edit()?.putInt("phone_number",binding.numberPhone.text.toString().toInt())?.apply()
            finish()
        }

        binding.exitFromApp.setOnClickListener {
            finish()
        }
    }

    private fun checkIsNumberCorrect(): Boolean {
        return binding.numberPhone.text.toString().length == 9
    }

    private fun isAgreeAndIsNumberCorrect(){

        binding.numberPhone.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.nextPage.isEnabled = (checkIsNumberCorrect() and binding.checkIsAgree.isChecked)
            }
        })

        binding.checkIsAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.nextPage.isEnabled = checkIsNumberCorrect() and isChecked
        }
    }

}