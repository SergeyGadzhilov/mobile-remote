package com.sg.mobile_remote.net.protocol.messages

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventEnter
import com.sg.mobile_remote.core.events.EventExit
import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.NetworkOutputMessage
import com.sg.mobile_remote.net.protocol.MessageTranslator
import com.sg.mobile_remote.net.protocol.MessageType
import com.sg.mobile_remote.net.protocol.ProtocolMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class COUT : MessageTranslator {
    override fun createEvent(message: ProtocolMessage): Event {
        return EventExit()
    }

    override fun createNetworkMessage(event: Event): NetworkOutputMessage {
        val message = NetworkOutputMessage()

        if (event.type() == EventType.Exit) {
            val event = event as EventEnter
            val messageData = ByteArrayOutputStream()

            val stream = DataOutputStream(messageData)
            stream.write(MessageType.COUT.toString().toByteArray())
            message.writeData(messageData)
        }

        return message
    }
}