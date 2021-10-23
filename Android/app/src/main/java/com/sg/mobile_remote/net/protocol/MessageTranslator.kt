package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.net.NetworkMessage

interface MessageTranslator {
    fun createEvent(message : NetworkMessage) : Event
    fun createNetworkMessage(event : Event) : NetworkMessage
}