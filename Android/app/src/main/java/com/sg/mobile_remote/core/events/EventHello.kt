package com.sg.mobile_remote.core.events

class EventHello(private val major : Short, private val minor : Short) : Event(EventType.Hello) {

    private var _name : String = ""

    fun getMajorVersion() : Short {
        return major
    }

    fun getMinorVersion(): Short {
        return minor
    }

    fun getName() : String {
        return _name
    }

    fun setName(name : String) {
        _name = name
    }

    override fun toString(): String {
        return "Type: ${type().toString()}; Major: ${major.toString()}; Minor: ${minor.toString()}"
    }
}