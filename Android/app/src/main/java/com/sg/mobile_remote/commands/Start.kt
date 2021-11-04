package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.controls.ButtonStart
import com.sg.mobile_remote.core.EventDispatcher
import com.sg.mobile_remote.core.EventListener
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventType

class Start(private val context: AppCompatActivity, private val _button : ButtonStart) :
    Command(), EventListener {

    init {
        _button.setText("Start")
        EventDispatcher.listenEvent(EventType.QueryInfo, this)
    }

    override fun run() {
        val startCommand = CheckDrawOverlays(context)
        startCommand.setNext(CheckAccessibilityService(context))
        startCommand.setNext(Connect(context, _button))
        startCommand.run()
    }

    override fun handleEvent(event: Event) {
        context.runOnUiThread( Runnable(){
            _button.enable()
            _button.setCommand(Stop(context, _button))
            EventDispatcher.removeListener(EventType.QueryInfo, this)
        })
    }
}