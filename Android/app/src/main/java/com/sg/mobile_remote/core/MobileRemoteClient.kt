package com.sg.mobile_remote.core

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.core.events.*
import com.sg.mobile_remote.net.Network
import com.sg.mobile_remote.net.NetworkRouter
import com.sg.mobile_remote.net.protocol.NetworkProtocol
import kotlin.concurrent.thread

class MobileRemoteClient() : EventListener {
    private var _isConnected = false
    private var _screen: Screen? = null
    private val _networkRouter = NetworkRouter(Network(), NetworkProtocol())

    init {
        EventDispatcher.listenEvent(EventType.Hello, this)
        EventDispatcher.listenEvent(EventType.QueryInfo, this)
        EventDispatcher.listenEvent(EventType.KeepAlive, this)
        EventDispatcher.listenEvent(EventType.Bye, this)
    }

    fun startClient(_context: AppCompatActivity) {
        _screen = Screen(_context)
        _networkRouter.connect("192.168.0.165", 24800)
    }

    fun stopClient() {
        _networkRouter.disconnect()
        _screen?.hide()
        _isConnected = false
    }

    private fun handleKeepAlive(event: EventKeepAlive) {
        val routerEvent = EventNetworkRouter(event)
        EventDispatcher.sendEvent(routerEvent)
    }

    override fun handleEvent(event: Event) {
        if (event.type() == EventType.Hello) {
            handleHello(event as EventHello)
        }
        else if (event.type() == EventType.QueryInfo) {
            handleQueryInfo(event as EventQueryInfo)
        }
        else if (event.type() == EventType.KeepAlive) {
            handleKeepAlive(event as EventKeepAlive)
        }
        else if (event.type() == EventType.Bye) {
            this.stopClient()
        }
    }

    private fun handleQueryInfo(event : EventQueryInfo) {
        _screen?.getHeight()?.let { event.setScreenHeight(it) }
        _screen?.getWidth()?.let { event.setScreenWidth(it) }
        _screen?.show()

        val routerEvent = EventNetworkRouter(event)
        EventDispatcher.sendEvent(routerEvent)
        _isConnected = true

        Log.i("SGADTRACE", "MobileRemoteClient: $event")
    }

    private fun handleHello(event : EventHello) {
        event.setName("Android")

        val routerEvent = EventNetworkRouter(event)
        EventDispatcher.sendEvent(routerEvent)

        Log.i("SGADTRACE", "MobileRemoteClient: $event")
    }

    fun isConnected() : Boolean {
        return _isConnected
    }

}