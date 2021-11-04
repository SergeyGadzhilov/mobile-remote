package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.ServiceClient
import com.sg.mobile_remote.controls.ButtonStart

class Stop(private val context: AppCompatActivity, private val _button : ButtonStart) : Command() {
    init {
        _button.setText("Stop")
    }

    override fun run() {
        ServiceClient.disconnect()
        _button.setCommand(Start(context, _button))
    }
}