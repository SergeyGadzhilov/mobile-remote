package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.net.NetworkOutputMessage

interface MessageTranslator {
    fun createEvent(message : ProtocolMessage) : Event
    fun createNetworkMessage(event : Event) : NetworkOutputMessage
}