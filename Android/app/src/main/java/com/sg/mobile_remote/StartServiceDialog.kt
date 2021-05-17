package com.sg.mobile_remote

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.DialogFragment

class StartServiceDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.app_name))
            builder.setMessage(getString(R.string.start_service_dialog_message))
            builder.setPositiveButton("ok") { dialog: DialogInterface, id: Int ->
                it.startActivityForResult(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), 0)
                dialog.cancel()
            }
            builder.create()
        }
    }
}