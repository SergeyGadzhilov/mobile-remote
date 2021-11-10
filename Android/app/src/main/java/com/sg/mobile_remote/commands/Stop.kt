package com.sg.mobile_remote.commands

import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.ServiceClient
import com.sg.mobile_remote.controls.ButtonStart
import com.sg.mobile_remote.core.EventDispatcher
import com.sg.mobile_remote.core.EventListener
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventType

class Stop(private val context: AppCompatActivity, private val _button : ButtonStart) : EventListener, Command() {
    init {
        _button.setText("Stop")
        registerEvents()
    }

    private fun registerEvents() {
        EventDispatcher.listenEvent(EventType.Bye, this)
    }

    private fun removeEvents() {
        EventDispatcher.removeListener(EventType.Bye, this)
    }

    override fun run() {
        removeEvents()
        context.runOnUiThread( Runnable(){
            ServiceClient.disconnect()
            _button.setCommand(Start(context, _button))
        })
    }

    override fun handleEvent(event: Event) {
        if (event.type() == EventType.Bye) {
            run()
        }
    }
}