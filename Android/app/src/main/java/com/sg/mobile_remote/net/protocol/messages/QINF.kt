package com.sg.mobile_remote.net.protocol.messages

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventQueryInfo
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.NetworkOutputMessage
import com.sg.mobile_remote.net.protocol.MessageTranslator
import com.sg.mobile_remote.net.protocol.MessageType
import com.sg.mobile_remote.net.protocol.ProtocolMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class QINF : MessageTranslator {
    override fun createEvent(message: ProtocolMessage): Event {
        return EventQueryInfo()
    }

    override fun createNetworkMessage(event: Event): NetworkOutputMessage {
        val message = NetworkOutputMessage()

        if (event.type() == EventType.QueryInfo) {
            val event = event as EventQueryInfo
            val messageData = ByteArrayOutputStream()

            val stream = DataOutputStream(messageData)
            stream.write(MessageType.DINF.toString().toByteArray())
            stream.writeShort(event.getScreenX())
            stream.writeShort(event.getScreenY())
            stream.writeShort(event.getScreenWidth())
            stream.writeShort(event.getScreenHeight())
            stream.writeShort(0)
            stream.writeShort(event.getCursorX())
            stream.writeShort(event.getCursorY())

            message.writeData(messageData)
        }

        return message
    }
}