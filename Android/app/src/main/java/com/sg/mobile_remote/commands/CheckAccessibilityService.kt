package com.sg.mobile_remote.commands

import android.content.Context
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
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
        val serviceID = context.getString(R.string.service_id)
        val service  = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        service.getEnabledAccessibilityServiceList(AccessibilityEvent.TYPES_ALL_MASK).forEach{
            if (serviceID == it.id) {
                return true
            }
        }
        return false
    }
}