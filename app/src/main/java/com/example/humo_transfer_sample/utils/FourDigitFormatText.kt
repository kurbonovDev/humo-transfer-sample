package com.example.humo_transfer_sample.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class FourDigitFormatText(private val editText: EditText):TextWatcher {
    private var isFormatting = false
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if (isFormatting) return

        isFormatting = true

        // Удаляем все пробелы из текста
        val text = s.toString().replace(" ", "")

        val formattedText = StringBuilder()

        // Добавляем пробел каждые четыре символа
        for (i in text.indices) {
            if (i > 0 && i % 4 == 0) {
                formattedText.append(" ")
            }
            formattedText.append(text[i])


        }

        // Устанавливаем текст с пробелами в EditText
        editText.setText(formattedText)
        // Перемещаем курсор в конец текста
        editText.setSelection(editText.text.length.coerceAtMost(19))


        isFormatting = false


    }
}