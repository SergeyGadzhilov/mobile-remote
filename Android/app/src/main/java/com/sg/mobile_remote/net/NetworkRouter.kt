package com.sg.mobile_remote.net

import android.util.Log
import com.sg.mobile_remote.core.EventDispatcher
import com.sg.mobile_remote.core.EventListener
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventNetworkRouter
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.protocol.NetworkProtocol

class NetworkRouter(private val _network: Network,
                    private val _protocol : NetworkProtocol) : EventListener {

    init {
        _network.setRouter(this)
        EventDispatcher.listenEvent(EventType.NetworkRouter, this)
    }

    fun connect(host: String, port: Int) {
        Log.i("SGADTRACE", "Connect started")
        _network.connect(host, port)
        Log.i("SGADTRACE" , "Connect finished")
    }

    fun disconnect() {
        _network.disconnect()
    }

    fun sendMessage(message: NetworkInputMessage) {
        val event = _protocol.createEvent(message)
        if (event != null) {
            EventDispatcher.sendEvent(event)
        }
        Log.i("SGADTRACE", "Server message: $event")
    }

    override fun handleEvent(event: Event) {
        if (event.type() == EventType.NetworkRouter) {
            val routerEvent = event as EventNetworkRouter
            val message = _protocol.createNetworkMessage(routerEvent.getEvent())
            if (message != null) {
                _network.sendMessage(message)
            }
        }
    }
}