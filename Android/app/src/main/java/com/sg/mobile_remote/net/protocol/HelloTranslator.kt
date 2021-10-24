package com.sg.mobile_remote.net.protocol

import android.util.Log
import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventHello
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.NetworkMessage

class HelloTranslator : MessageTranslator{
    override fun createEvent(message: ProtocolMessage) : EventHello {
        val major = message.readShort()
        val minor = message.readShort()

        return EventHello(major, minor)
    }

    override fun createNetworkMessage(event: Event): NetworkMessage {
        return NetworkMessage()
    }
}