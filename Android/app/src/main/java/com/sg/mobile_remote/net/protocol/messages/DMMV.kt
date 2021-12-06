package com.sg.mobile_remote.net.protocol.messages

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventEnter
import com.sg.mobile_remote.core.events.EventMouseMove
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.NetworkOutputMessage
import com.sg.mobile_remote.net.protocol.MessageTranslator
import com.sg.mobile_remote.net.protocol.MessageType
import com.sg.mobile_remote.net.protocol.ProtocolMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class DMMV : MessageTranslator {
    override fun createEvent(message: ProtocolMessage): Event {
        val event = EventMouseMove()
        event.setX(message.readShort().toInt())
        event.setY(message.readShort().toInt())

        return event
    }

    override fun createNetworkMessage(event: Event): NetworkOutputMessage {
        val message = NetworkOutputMessage()

        if (event.type() == EventType.MouseMove) {
            val event = event as EventMouseMove
            val messageData = ByteArrayOutputStream()

            val stream = DataOutputStream(messageData)
            stream.write(MessageType.DMMV.toString().toByteArray())
            stream.writeShort(event.getX())
            stream.writeShort(event.getY())
            message.writeData(messageData)
        }

        return message
    }
}