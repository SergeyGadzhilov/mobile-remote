package com.sg.mobile_remote

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.sg.mobile_remote.core.MobileRemoteClient
import kotlin.concurrent.thread

class SGMobileRemoteService() : AccessibilityService() {
    private var _client = MobileRemoteClient()

    override fun onServiceConnected() {
        Log.i("SGADTRACE", "onAccessibilityEvent")
        ServiceClient.bind(this)
        super.onServiceConnected()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.i("SGADTRACE", "onAccessibilityEvent")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("SGADTRACE", "onUnbind")
        ServiceClient.unbind()
        return super.onUnbind(intent)
    }

    override fun onInterrupt() {
        Log.i("SGADTRACE", "onInterrupt")
        ServiceClient.unbind()
    }

    fun connect() {
        Log.i("SGADTRACE", "SGMobileRemoteService::connect")
        _client.startClient()
    }

    fun disconnect() {
        Log.i("SGADTRACE", "SGMobileRemoteService::disconnect")
        _client.stopClient()
    }

    fun isConnected() : Boolean {
        return _client.isConnected()
    }
}
