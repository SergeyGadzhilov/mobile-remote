package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.ServiceClient
import com.sg.mobile_remote.controls.ButtonStart

class Stop(private val context: AppCompatActivity, private val _button : ButtonStart) : Command() {
    override fun run() {
        ServiceClient.disconnect()
        _button.setText("Start")
        _button.setCommand(Start(context, _button))
    }
}