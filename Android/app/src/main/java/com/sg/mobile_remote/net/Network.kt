package com.sg.mobile_remote.net

import android.util.Log
import com.sg.mobile_remote.core.EventDispatcher
import com.sg.mobile_remote.core.events.EventConnectionError
import java.io.IOException
import java.net.Socket
import kotlin.concurrent.thread

class Network {
    private var _socket : Socket? = null
    private var _router : NetworkRouter? = null

    fun connect(host : String, port : Int){
        thread{
            try {
                _socket = Socket(host, port)
                startRead()
            }
            catch(e : IOException) {
                Log.e("SGADTRACE", e.toString())
                EventDispatcher.sendEvent(EventConnectionError(e.toString()))
            }
        }
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
            if (_socket?.getInputStream()?.available()!! > 0) {
                val message = NetworkInputMessage(_socket?.getInputStream())
                _router?.sendMessage(message)
            }
        }
        Log.i("SGADTRACE", "sockerReading finished")
    }
}
