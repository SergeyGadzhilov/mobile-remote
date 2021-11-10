package com.sg.mobile_remote.dialogs

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity

class ErrorDialog(private val _context : AppCompatActivity, private val _message : String) {
    fun show() {
        val dialog = AlertDialog.Builder(_context)
        dialog.setIcon(android.R.drawable.ic_delete)
        dialog.setTitle("Error")
        dialog.setMessage(_message)
        dialog.setCancelable(true)

        dialog.setPositiveButton("ok") { dialog: DialogInterface, id: Int ->
            dialog.cancel()
        }

        dialog.create().show()
    }
}