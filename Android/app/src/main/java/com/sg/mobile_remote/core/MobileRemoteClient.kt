package com.sg.mobile_remote.core

import android.util.Log
import com.sg.mobile_remote.core.events.*
import com.sg.mobile_remote.net.Network
import com.sg.mobile_remote.net.NetworkRouter
import com.sg.mobile_remote.net.protocol.NetworkProtocol

class MobileRemoteClient : Thread(), EventListener {
    private val _screen = Screen()
    private val _eventDispatcher = EventDispatcher()
    private val _networkRouter = NetworkRouter(Network(), NetworkProtocol(), _eventDispatcher)

    override fun run() {
        _eventDispatcher.listenEvent(EventType.Hello, this)
        _eventDispatcher.listenEvent(EventType.QueryInfo, this)
        _networkRouter.connect("192.168.0.165", 24800)
    }

    override fun handleEvent(event: Event) {
        if (event.type() == EventType.Hello) {
            handleHello(event as EventHello)
        }
        else if (event.type() == EventType.QueryInfo) {
            handleQueryInfo(event as EventQueryInfo)
        }
    }

    private fun handleQueryInfo(event : EventQueryInfo) {
        event.setScreenHeight(_screen.getHeight())
        event.setScreenWidth(_screen.getWidth())

        val routerEvent = EventNetworkRouter(event)
        _eventDispatcher.sendEvent(routerEvent)

        Log.i("SGADTRACE", "MobileRemoteClient: $event")
    }

    private fun handleHello(event : EventHello) {
        event.setName("Android")

        val routerEvent = EventNetworkRouter(event)
        _eventDispatcher.sendEvent(routerEvent)

        Log.i("SGADTRACE", "MobileRemoteClient: $event")
    }

}