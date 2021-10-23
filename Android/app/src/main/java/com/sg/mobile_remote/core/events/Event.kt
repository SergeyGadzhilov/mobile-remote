package com.sg.mobile_remote.core.events

open class Event(private val _type: EventType = EventType.None) {
    fun type(): EventType {
        return _type
    }
}