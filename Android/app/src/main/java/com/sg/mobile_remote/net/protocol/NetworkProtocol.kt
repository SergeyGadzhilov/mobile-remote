package com.sg.mobile_remote.net.protocol

import android.util.Log
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.net.NetworkInputMessage
import com.sg.mobile_remote.net.NetworkOutputMessage
import java.lang.IllegalArgumentException

class NetworkProtocol {
    private val _translatorsFactory = MessageFactory()

    fun createEvent(message: NetworkInputMessage) : Event? {
        var event : Event? = null
        try {
            val protocolMessage = ProtocolMessage(message)
            val translator = _translatorsFactory.createTranslator(protocolMessage.getType())
            event = translator?.createEvent(protocolMessage)
        }
        catch (e: IllegalArgumentException) {
            Log.i("SGADTRACE", e.toString())
         }

        return event
    }

    fun createNetworkMessage(event: Event) : NetworkOutputMessage? {
        val translator = _translatorsFactory.createTranslator(event.type())
        return translator?.createNetworkMessage(event)
    }
}