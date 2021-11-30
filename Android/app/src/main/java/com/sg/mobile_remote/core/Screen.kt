package com.sg.mobile_remote.core

import android.content.res.Resources
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.R

class Screen(private val _context: AppCompatActivity) {

    fun getHeight() : Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getWidth() : Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun show() {
        _context.runOnUiThread(Runnable {
            val cursorView = View.inflate(_context.baseContext, R.layout.cursor, null)
            val cursorLayout = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT)

            cursorLayout.gravity = Gravity.TOP and Gravity.START
            cursorLayout.x = 200;
            cursorLayout.y = 200;
            _context.windowManager.addView(cursorView, cursorLayout)
        })
    }
}