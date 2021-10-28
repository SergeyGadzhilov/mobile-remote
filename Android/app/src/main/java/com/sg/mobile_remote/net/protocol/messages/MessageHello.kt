package com.sg.mobile_remote.net.protocol.messages

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventHello
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.NetworkOutputMessage
import com.sg.mobile_remote.net.protocol.MessageTranslator
import com.sg.mobile_remote.net.protocol.MessageType
import com.sg.mobile_remote.net.protocol.ProtocolMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class MessageHello : MessageTranslator {
    override fun createEvent(message: ProtocolMessage) : EventHello {
        val major = message.readShort()
        val minor = message.readShort()

        return EventHello(major, minor)
    }

    override fun createNetworkMessage(event: Event): NetworkOutputMessage {
        val message = NetworkOutputMessage()

        if (event.type() == EventType.Hello) {
            val helloEvent = event as EventHello
            val messageData = ByteArrayOutputStream()

            val stream = DataOutputStream(messageData)
            stream.write(MessageType.Synergy.toString().toByteArray())
            stream.writeShort(helloEvent.getMajorVersion().toInt())
            stream.writeShort(helloEvent.getMinorVersion().toInt())
            stream.writeInt(helloEvent.getName().length)
            stream.write(helloEvent.getName().toByteArray())

            message.writeData(messageData)
        }

        return message
    }
}