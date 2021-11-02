package com.sg.mobile_remote.controls

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.R
import com.sg.mobile_remote.commands.Command
import com.sg.mobile_remote.commands.Start

class ButtonStart(private val _context : AppCompatActivity) {
    private val _button = _context.findViewById<Button>(R.id.button_start)
    private var _command : Command = Start(_context, this)

    fun click() {
        _command.run()
    }

    fun setText(text : String) {
        _button.setText(text)
    }

    fun setCommand(command: Command) {
        _command = command
    }

}