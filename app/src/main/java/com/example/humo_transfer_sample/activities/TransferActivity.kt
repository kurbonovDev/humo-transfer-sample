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


        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        val intent = intent
        val nameCountry = intent.getStringExtra("name_country")
        val initialCountry = intent.getStringExtra("initial_country")
        val courseCountry = intent.getStringExtra("course_country")?.toDoubleOrNull() ?: 0.0

        binding.txtPerevod.text = "Перевод в $nameCountry"
        binding.course.text = "1 TJS = $courseCountry $initialCountry"
        binding.back.setOnClickListener {
            finish()
        }

        binding.btnSendPerevod.setOnClickListener {

            val cardNumber = binding.numberCard.text.toString()
            val sum = binding.sumaPerevoda.text.toString()

            if (cardNumber.length == 19) {
                if (sum.isNotEmpty()) {
                    Toast.makeText(this, "Перевод успешно выполнен", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Введите сумму", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Заполните правильно поля ввода номера карты",
                    Toast.LENGTH_SHORT
                ).show()
            }


            binding.numberCard.addTextChangedListener(FourDigitFormatText(binding.numberCard))
        }
    }
}