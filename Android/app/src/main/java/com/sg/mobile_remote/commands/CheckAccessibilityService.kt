package com.sg.mobile_remote.commands

import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.R
import com.sg.mobile_remote.StartServiceDialog

class CheckAccessibilityService(private val context: AppCompatActivity) : Command, CommandsChain() {
    override fun run() {
        if (isEnabled()) {
            next()
        }
        else {
            val dialog = StartServiceDialog()
            dialog.show(context.supportFragmentManager, "StartServiceDialog")
        }
    }

    private fun isEnabled() : Boolean {
        var isEnabled = false

        if (Settings.Secure.getInt(context.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED) != 0) {
            val services = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
            isEnabled = services.contains(context.getString(R.string.service_name))
        }

        return isEnabled
    }
}