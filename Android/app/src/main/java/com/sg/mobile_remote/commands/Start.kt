package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.controls.ButtonStart

class Start(private val context: AppCompatActivity, private val _button : ButtonStart) : Command() {
    override fun run() {
        val startCommand = CheckDrawOverlays(context)
        startCommand.setNext(CheckAccessibilityService(context))
        startCommand.setNext(Connect(context, _button))
        startCommand.run()
    }
}