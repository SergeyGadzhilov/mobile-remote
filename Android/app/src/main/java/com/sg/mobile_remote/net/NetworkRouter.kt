package com.sg.mobile_remote.net

import android.util.Log
import com.sg.mobile_remote.net.protocol.NetworkProtocol

class NetworkRouter(private val _network: Network, private val _protocol : NetworkProtocol) {

    init {
        _network.setRouter(this)
    }

    fun connect(host: String, port: Int) {
        _network.connect(host, port)
    }

    fun sendMessage(message: NetworkMessage) {
        val event = _protocol.createEvent(message)
        Log.i("SGADTRACE", "Server message $event")
    }
}