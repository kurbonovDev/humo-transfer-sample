package com.example.humo_transfer_sample.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.humo_transfer_sample.databinding.ExitDialogBinding

object ExitDialog {
    fun showDialog(context: Context, listener: Listener) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)

        val binding = ExitDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            bDelete.setOnClickListener {
                listener.onClick()
                dialog?.dismiss()
            }
            bCancel.setOnClickListener {
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }

    interface Listener {
        fun onClick()
    }

}