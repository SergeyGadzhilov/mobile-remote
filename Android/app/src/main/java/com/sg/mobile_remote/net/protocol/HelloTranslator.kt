package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventHello
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.NetworkMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class HelloTranslator : MessageTranslator{
    override fun createEvent(message: ProtocolMessage) : EventHello {
        val major = message.readShort()
        val minor = message.readShort()

        return EventHello(major, minor)
    }

    override fun createNetworkMessage(event: Event): NetworkMessage {
        val message = NetworkMessage()

        if (event.type() == EventType.Hello) {
            val helloEvent = event as EventHello
            val messageData = ByteArrayOutputStream()

            val stream = DataOutputStream(messageData)
            stream.write("Synergy".toByteArray())
            stream.writeShort(helloEvent.getMajorVersion().toInt())
            stream.writeShort(helloEvent.getMinorVersion().toInt())
            stream.writeInt(helloEvent.getName().length)
            stream.write(helloEvent.getName().toByteArray())

            val messageStream = DataOutputStream(message)
            messageStream.writeInt(stream.size())
            messageData.writeTo(messageStream)
        }

        return message
    }
}