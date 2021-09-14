package com.sg.mobile_remote

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class SGMobileRemoteService() : AccessibilityService() {

    override fun onServiceConnected() {
        Log.i("SGADTRACE", "onAccessibilityEvent")
        ServiceBinder.setService(this)
        super.onServiceConnected()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.i("SGADTRACE", "onAccessibilityEvent")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("SGADTRACE", "onUnbind")
        ServiceBinder.setService(null)
        return super.onUnbind(intent)
    }

    override fun onInterrupt() {
        Log.i("SGADTRACE", "onInterrupt")
        ServiceBinder.setService(null)
    }

    fun connect() {
        Log.i("SGADTRACE", "SGMobileRemoteService::connect")
    }
}
