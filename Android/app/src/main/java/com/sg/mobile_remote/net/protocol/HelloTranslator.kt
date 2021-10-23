package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventHello
import com.sg.mobile_remote.net.NetworkMessage

class HelloTranslator : MessageTranslator{
    override fun createEvent(message: NetworkMessage) : EventHello {
        return EventHello(1,5)
    }

    override fun createNetworkMessage(event: Event): NetworkMessage {
        TODO("Not yet implemented")
    }
}