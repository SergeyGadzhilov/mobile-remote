package com.sg.mobile_remote.core

import android.util.Log
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventHello
import com.sg.mobile_remote.core.events.EventNetworkRouter
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.Network
import com.sg.mobile_remote.net.NetworkRouter
import com.sg.mobile_remote.net.protocol.NetworkProtocol

class MobileRemoteClient : Thread(), EventListener {
    private val _eventDispatcher = EventDispatcher()
    private val _networkRouter = NetworkRouter(Network(), NetworkProtocol(), _eventDispatcher)

    override fun run() {
        _eventDispatcher.listenEvent(EventType.Hello, this)
        _networkRouter.connect("192.168.0.165", 24800)
    }

    override fun handleEvent(event: Event) {
        if (event.type() == EventType.Hello) {
            val helloMessage = event as EventHello
            helloMessage.setName("Android")

            val routerEvent = EventNetworkRouter(helloMessage)
            _eventDispatcher.sendEvent(routerEvent)

            Log.i("SGADTRACE", "MobileRemoteClient: $helloMessage")
        }
    }
}