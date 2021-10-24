package com.sg.mobile_remote.core.events

class EventHello(private val major : Short, private val minor : Short) : Event(EventType.Hello) {

    fun getMajorVersion() : Short {
        return major
    }

    fun getMinorVersion(): Short {
        return minor
    }

    override fun toString(): String {
        return "Type: ${type().toString()}; Major: ${major.toString()}; Minor: ${minor.toString()}"
    }
}