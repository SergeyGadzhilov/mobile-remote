package com.sg.mobile_remote.core

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity

class Screen(private val _context: AppCompatActivity) {
    private val _mouse : Mouse = Mouse(_context)

    fun getHeight() : Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getWidth() : Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun show() {
        this._mouse.show()
    }

    fun hide() {
        this._mouse.hide()
    }

}
