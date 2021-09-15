package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity

class Start(private val context: AppCompatActivity) : Command() {
    override fun run() {
        val startCommand = CheckDrawOverlays(context)
        startCommand.setNext(CheckAccessibilityService(context))
        startCommand.setNext(Connect())
        startCommand.run()
    }
}