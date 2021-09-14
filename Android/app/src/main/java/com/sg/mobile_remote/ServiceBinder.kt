package com.sg.mobile_remote

object ServiceBinder{
    var m_service : SGMobileRemoteService? = null

    fun setService(service : SGMobileRemoteService?){
        m_service = service;
    }

    fun getService(): SGMobileRemoteService? {
        return m_service;
    }
}