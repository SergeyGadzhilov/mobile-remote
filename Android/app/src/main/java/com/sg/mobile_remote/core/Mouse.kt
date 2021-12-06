package com.sg.mobile_remote.core

import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.R
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventMouseMove
import com.sg.mobile_remote.core.events.EventType

class Mouse(private val _context: AppCompatActivity) : EventListener {
    private var _cursor : View? = null
    private var _cursorLayout : WindowManager.LayoutParams? = null

    init {
        EventDispatcher.listenEvent(EventType.MouseMove, this)
    }

    fun show(x: Short, y: Short) {
        _context.runOnUiThread(Runnable {
            this._cursor = View.inflate(_context.baseContext, R.layout.cursor, null)
            this._cursorLayout = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT)

            this._cursorLayout!!.gravity = Gravity.TOP and Gravity.START
            this._cursorLayout!!.x = x.toInt();
            this._cursorLayout!!.y = y.toInt();
            _context.windowManager.addView(this._cursor, this._cursorLayout)
        })
    }

    fun move(x: Int, y: Int) {
        if (this._cursorLayout != null) {
            this._cursorLayout?.x = x.toInt()
            this._cursorLayout?.y = y.toInt()
            _context.runOnUiThread(Runnable {
                _context.windowManager.updateViewLayout(this._cursor, this._cursorLayout)
            })
        }
    }

    fun hide() {
        _context.runOnUiThread(Runnable {
            if (this._cursor != null) {
                _context.windowManager.removeView(_cursor)
                this._cursor = null
                this._cursorLayout = null
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