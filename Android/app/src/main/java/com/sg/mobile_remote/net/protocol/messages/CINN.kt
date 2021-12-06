package com.sg.mobile_remote.net.protocol.messages

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventEnter
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.NetworkOutputMessage
import com.sg.mobile_remote.net.protocol.MessageTranslator
import com.sg.mobile_remote.net.protocol.MessageType
import com.sg.mobile_remote.net.protocol.ProtocolMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class CINN : MessageTranslator {
    override fun createEvent(message: ProtocolMessage): Event {
        val event = EventEnter()

        event.setX(message.readShort())
        event.setY(message.readShort())
        event.setSequence(message.readInt())
        event.setModifiers(message.readShort())

        return event
    }

    override fun createNetworkMessage(event: Event): NetworkOutputMessage {
        val message = NetworkOutputMessage()

        if (event.type() == EventType.Enter) {
            val event = event as EventEnter
            val messageData = ByteArrayOutputStream()

            val stream = DataOutputStream(messageData)
            stream.write(MessageType.CINN.toString().toByteArray())
            stream.writeShort(event.getX().toInt())
            stream.writeShort(event.getY().toInt())
            stream.writeInt(event.getSequence())
            stream.writeShort(event.getModifiers().toInt())

            message.writeData(messageData)
        }

        return message
    }

}