package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.net.NetworkMessage

class NetworkProtocol {
    private val _translatorsFactory = TranslatorFactory()

    fun createEvent(message: NetworkMessage) : Event {
        val protocolMessage = ProtocolMessage(message)
        val translator = _translatorsFactory.createTranslator(protocolMessage.getType())
        return translator?.createEvent(protocolMessage)!!
    }

    fun createNetworkMessage(event: Event) : NetworkMessage? {
        val translator = _translatorsFactory.createTranslator(event.type())
        return translator?.createNetworkMessage(event)
    }
}