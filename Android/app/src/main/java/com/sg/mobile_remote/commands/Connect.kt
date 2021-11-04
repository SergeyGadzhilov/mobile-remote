package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.ServiceClient
import com.sg.mobile_remote.controls.ButtonStart

class Connect(private val context: AppCompatActivity, private val _button : ButtonStart) : Command() {
    override fun run() {
        _button.disable()
        ServiceClient.connect()
    }
}