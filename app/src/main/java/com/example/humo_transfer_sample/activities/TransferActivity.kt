package com.example.humo_transfer_sample.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.databinding.ActivityTransferBinding
import com.example.humo_transfer_sample.utils.FourDigitFormatText

class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        val intent=intent
        val nameCountry=intent.getStringExtra("name_country")
        val initialCountry=intent.getStringExtra("initial_country")
        val courseCountry=intent.getDoubleExtra("course_country",0.0)

        binding.txtPerevod.text="Перевод в $nameCountry"
        binding.course.text="1 TJS = $courseCountry $initialCountry"
        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.btnSendPerevod.setOnClickListener {
            Toast.makeText(this,"Перевод успешно выполнен",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        binding.numberCard.addTextChangedListener(FourDigitFormatText(binding.numberCard))
    }
}