package com.sg.mobile_remote

import androidx.appcompat.app.AppCompatActivity

object ServiceClient{
    private var m_service : SGMobileRemoteService? = null

    fun bind(service : SGMobileRemoteService?){
        m_service = service;
    }

    fun unbind() {
        m_service = null;
    }

    fun connect(_context: AppCompatActivity) {
        m_service?.connect(_context)
    }

    fun disconnect() {
        m_service?.disconnect()
    }

    fun isConnected() : Boolean {
        var connected = false

        if (m_service != null) {
            connected = (m_service?.isConnected() == true)
        }
        return connected
    }
}