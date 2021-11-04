package com.sg.mobile_remote.core

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventType
import java.util.*

object EventDispatcher {
    private val _listeners = mutableMapOf<EventType, MutableSet<EventListener>>()

    fun listenEvent(type : EventType, listener: EventListener) {
        synchronized(_listeners) {
            if (!_listeners.containsKey(type)) {
                _listeners[type] = mutableSetOf<EventListener>(listener)
            } else {
                _listeners[type]?.add(listener)
            }
        }
    }

    fun sendEvent(event : Event) {
        synchronized(_listeners) {
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
}