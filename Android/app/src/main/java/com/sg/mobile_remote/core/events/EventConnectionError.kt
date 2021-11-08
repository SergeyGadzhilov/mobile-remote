package com.sg.mobile_remote.core.events

class EventConnectionError(private val _message : String) : Event(EventType.ConnectionError) {
    fun getMessage() : String {
        return _message
    }
}