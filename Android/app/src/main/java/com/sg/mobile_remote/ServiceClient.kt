package com.sg.mobile_remote

object ServiceClient{
    private var m_service : SGMobileRemoteService? = null

    fun bind(service : SGMobileRemoteService?){
        m_service = service;
    }

    fun unbind() {
        m_service = null;
    }

    fun connect() {
        m_service?.connect()
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