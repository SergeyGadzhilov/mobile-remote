package com.sg.mobile_remote.core

import android.content.res.Resources

class Screen {

    fun getHeight() : Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getWidth() : Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }
}