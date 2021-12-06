package com.sg.mobile_remote.core

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventEnter
import com.sg.mobile_remote.core.events.EventType

class Screen(private val _context: AppCompatActivity) : EventListener {
    private val _mouse : Mouse = Mouse(_context)

    init {
        EventDispatcher.listenEvent(EventType.Enter, this)
        EventDispatcher.listenEvent(EventType.Exit, this)
    }

    fun getHeight() : Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getWidth() : Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun show(event: EventEnter) {
        this._mouse.show(event.getX(), event.getY())
    }

    fun hide() {
        this._mouse.hide()
    }

    override fun handleEvent(event: Event) {
        when(event.type()) {
            EventType.Enter -> this.show(event as EventEnter)
            EventType.Exit -> this.hide()
        }
    }

}
