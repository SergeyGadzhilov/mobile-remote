package com.sg.mobile_remote.core

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventType
import java.util.*

class EventDispatcher {
    private val _listeners = mutableMapOf<EventType, Vector<EventListener>>()

    fun listenEvent(type : EventType, listener: EventListener) {
        if (!_listeners.containsKey(type)) {
            _listeners[type] = Vector<EventListener>()

        }
        _listeners[type]?.add(listener)
    }

    fun sendEvent(event : Event) {
        if (_listeners.containsKey(event.type())) {
            val items = _listeners[event.type()]
            if (items != null) {
                for(i in items) {
                    i.handleEvent(event)
                }
            }
        }
    }
}