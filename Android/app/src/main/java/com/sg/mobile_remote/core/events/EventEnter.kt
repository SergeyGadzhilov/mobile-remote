package com.sg.mobile_remote.core.events

class EventEnter : Event(EventType.Enter) {
    private var _x: Short = 0
    private var _y: Short = 0
    private var _sequence = 0
    private var _modifiers: Short = 0

    fun getX(): Short {
        return this._x
    }

    fun getY(): Short {
        return this._y
    }

    fun getSequence() : Int {
        return this._sequence
    }

    fun getModifiers() : Short {
        return this._modifiers
    }

    fun setX(x: Short) {
        this._x = x
    }

    fun setY(y: Short) {
        this._y = y
    }

    fun setSequence(value: Int) {
        this._sequence = value
    }

    fun setModifiers(modifiers: Short) {
        this._modifiers = modifiers
    }

    override fun toString(): String {
        return "Event enter-> X: ${this._x}; Y: ${this._y}; Sequence:${this._sequence} Modifiers: ${this._modifiers}"
    }
}