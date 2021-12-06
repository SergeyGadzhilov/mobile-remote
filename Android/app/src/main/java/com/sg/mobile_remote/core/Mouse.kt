package com.sg.mobile_remote.core

import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.R

class Mouse(private val _context: AppCompatActivity) {
    private var _cursor : View? = null

    fun show(x: Short, y: Short) {
        _context.runOnUiThread(Runnable {
            this._cursor = View.inflate(_context.baseContext, R.layout.cursor, null)
            val cursorLayout = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT)

            cursorLayout.gravity = Gravity.TOP and Gravity.START
            cursorLayout.x = x.toInt();
            cursorLayout.y = y.toInt();
            _context.windowManager.addView(_cursor, cursorLayout)
        })
    }

    fun hide() {
        _context.runOnUiThread(Runnable {
            if (this._cursor != null) {
                _context.windowManager.removeView(_cursor)
                this._cursor = null
            }
        })
    }
}