package com.sg.mobile_remote.core.events

class EventQueryInfo : Event(EventType.QueryInfo) {
    private var _screenX = 0
    private var _screenY = 0
    private var _screenWidth = 100
    private var _screenHeight = 100
    private var _unknown = 0
    private var _cursorX = 0
    private var _cursorY = 0

    fun setScreenX(screenX : Int) {
        _screenX = screenX
    }

    fun setScreenY(screenY : Int) {
        _screenY = screenY
    }

    fun setScreenWidth(screenWidth : Int) {
        _screenWidth = screenWidth
    }

    fun setScreenHeight(screenHeight : Int) {
        _screenHeight = screenHeight
    }

    fun setUnknown(unknown : Int) {
        _unknown = unknown
    }

    fun setCursorX(cursorX : Int) {
        _cursorX = cursorX
    }

    fun setCursorY(cursorY : Int) {
        _cursorY = cursorY
    }

    fun getScreenX() : Int {
        return _screenX
    }

    fun getScreenY() : Int {
        return _screenY
    }

    fun getScreenWidth() : Int {
        return _screenWidth
    }

    fun getScreenHeight() : Int {
        return _screenHeight
    }

    fun getUnknown() : Int {
        return _unknown
    }

    fun getCursorX() : Int {
        return _cursorX
    }

    fun getCursorY() : Int {
        return _cursorY
    }
}