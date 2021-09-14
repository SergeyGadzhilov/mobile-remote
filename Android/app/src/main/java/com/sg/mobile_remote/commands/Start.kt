package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity

class Start(private val context: AppCompatActivity) : Command() {
    override fun run() {
        CheckDrawOverlays(context).setNext(
            CheckAccessibilityService(context).setNext(
                Connect()
        )).run()
    }
}