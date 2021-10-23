package com.sg.mobile_remote.core.events

class EventHello(private val major : Int, private val minor : Int) : Event(EventType.Hello) {

    fun getMajorVersion() : Int {
        return major
    }

    fun getMinorVersion(): Int {
        return minor
    }

    override fun toString(): String {
        return "Type: ${type().toString()}; Major: ${major.toString()}; Minor: ${minor.toString()}"
    }
}