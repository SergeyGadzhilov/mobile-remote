package com.sg.mobile_remote.commands

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Start(private val context: AppCompatActivity) : Command {
    override fun run() {
        val startCommand = CheckDrawOverlays(context)
        startCommand.setNext(CheckAccessibilityService(context))
        startCommand.run()
    }
}