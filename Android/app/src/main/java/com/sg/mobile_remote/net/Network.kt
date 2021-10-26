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

    fun sendMessage(message : NetworkOutputMessage) {
        val stream = _socket?.getOutputStream()
        stream?.write(message.toByteArray())
        stream?.flush()

        Log.i("SGADTRACE", "Send message: $message")
    }

    fun disconnect() {
        _socket?.close()
    }

    fun setRouter(router: NetworkRouter) {
        _router = router
    }

    private fun startRead() {
        while(_socket?.isClosed == false) {
            val message = NetworkInputMessage(_socket?.getInputStream())
            _router?.sendMessage(message)
        }
    }
}