package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.controls.ButtonStart
import com.sg.mobile_remote.core.EventDispatcher
import com.sg.mobile_remote.core.EventListener
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventConnectionError
import com.sg.mobile_remote.core.events.EventQueryInfo
import com.sg.mobile_remote.core.events.EventType

class Start(private val context: AppCompatActivity, private val _button : ButtonStart) :
    Command(), EventListener {

    init {
        _button.setText("Start")

    }

    private fun registerEvents() {
        EventDispatcher.listenEvent(EventType.QueryInfo, this)
        EventDispatcher.listenEvent(EventType.ConnectionError, this)
    }

    private fun removeEvents() {
        EventDispatcher.removeListener(EventType.QueryInfo, this)
        EventDispatcher.removeListener(EventType.ConnectionError, this)
    }


    override fun run() {
        this.registerEvents()
        val startCommand = CheckDrawOverlays(context)
        startCommand.setNext(CheckAccessibilityService(context))
        startCommand.setNext(Connect(context, _button))
        startCommand.run()
    }

    override fun handleEvent(event: Event) {
        if (event.type() == EventType.ConnectionError) {
            handleConnectionFailed(event as EventConnectionError)
        }
        else if (event.type() == EventType.QueryInfo) {
            handleStarted(event as EventQueryInfo)
        }
    }

    private fun handleStarted(event: EventQueryInfo) {
        removeEvents()
        context.runOnUiThread( Runnable(){
            _button.enable()
            _button.setCommand(Stop(context, _button))
        })
    }

    private fun handleConnectionFailed(event : EventConnectionError) {
        removeEvents()
        context.runOnUiThread( Runnable(){_button.enable()})
    }
}