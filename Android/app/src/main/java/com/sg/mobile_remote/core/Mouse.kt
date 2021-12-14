package com.sg.mobile_remote.core

import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.R
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventMouseMove
import com.sg.mobile_remote.core.events.EventType

class Mouse(private val _context: AppCompatActivity) : EventListener {
    private val _cursor : View = View.inflate(_context.baseContext, R.layout.cursor, null)

    init {
        EventDispatcher.listenEvent(EventType.MouseMove, this)
    }

    fun show(x: Short, y: Short) {
        _context.runOnUiThread(Runnable {
            if (this._cursor.parent == null) {
                val params = this.getCursorParams(x.toInt(), y.toInt())
                _context.windowManager.addView(this._cursor, params)
            }
        })
    }

    private fun getCursorParams(x: Int, y: Int): WindowManager.LayoutParams {
        val params = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                                                WindowManager.LayoutParams.WRAP_CONTENT,
                                                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                                PixelFormat.TRANSLUCENT)

        params.gravity = Gravity.TOP or Gravity.START
        params.x = x;
        params.y = y;

        return params
    }

    private fun move(x: Int, y: Int) {
        _context.runOnUiThread(Runnable {
            if (this._cursor.parent != null) {
                val params = this.getCursorParams(x, y)
                _context.windowManager.updateViewLayout(this._cursor, params)
            }
        })
    }

    fun hide() {
        _context.runOnUiThread(Runnable {
            if (this._cursor.parent != null) {
                _context.windowManager.removeViewImmediate(this._cursor)
            }
        })
    }

    override fun handleEvent(event: Event) {
        if (event.type() == EventType.MouseMove) {
            val move = event as EventMouseMove
            this.move(move.getX(), move.getY())
        }
    }
}