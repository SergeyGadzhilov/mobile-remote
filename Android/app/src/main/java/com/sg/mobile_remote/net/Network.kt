package com.sg.mobile_remote.net

import android.util.Log
import java.net.Socket
import kotlin.concurrent.thread

class Network {
    private var _socket : Socket? = null
    private var _router : NetworkRouter? = null

    fun connect(host : String, port : Int){
        _socket = Socket(host, port)
        thread{startRead()}
    }

    fun sendMessage(message : NetworkMessage) {
        Log.i("SGADTRACE", "Send message")
    }

    fun disconnect() {
        _socket?.close()
    }

    fun setRouter(router: NetworkRouter) {
        _router = router
    }

    private fun startRead() {
        val message = NetworkMessage()
        while(_socket?.isClosed == false) {
            message.add(_socket?.getInputStream()?.read())
            if (_socket?.getInputStream()?.available()!! <= 0) {
                _router?.sendMessage(message)
                message.clear()
            }
        }
    }
}
