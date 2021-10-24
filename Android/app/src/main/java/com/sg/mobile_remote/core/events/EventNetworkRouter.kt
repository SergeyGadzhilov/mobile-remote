package com.sg.mobile_remote.core.events

class EventNetworkRouter(private val _event : Event) : Event(EventType.NetworkRouter) {
    fun getEvent() : Event {
        return _event
    }

    override fun toString(): String {
        return "EventNetworkRouter: $_event"
    }
}