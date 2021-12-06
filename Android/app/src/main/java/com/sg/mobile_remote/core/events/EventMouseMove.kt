package com.sg.mobile_remote.core.events

class EventMouseMove : Event(EventType.MouseMove) {
    private var _x = 0;
    private var _y = 0;

    fun setX(x: Int) {
        this._x = x
    }

    fun setY(y: Int) {
        this._y = y
    }

    fun getX() : Int {
        return this._x
    }

    fun getY() : Int {
        return this._y
    }
}