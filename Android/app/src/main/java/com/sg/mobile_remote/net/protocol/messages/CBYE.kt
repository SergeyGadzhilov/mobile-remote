package com.sg.mobile_remote.net.protocol.messages

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventBye
import com.sg.mobile_remote.net.NetworkOutputMessage
import com.sg.mobile_remote.net.protocol.MessageTranslator
import com.sg.mobile_remote.net.protocol.MessageType
import com.sg.mobile_remote.net.protocol.ProtocolMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class CBYE : MessageTranslator {
    override fun createEvent(message: ProtocolMessage): Event {
        return EventBye()
    }

    override fun createNetworkMessage(event: Event): NetworkOutputMessage {
        val messageData = ByteArrayOutputStream()
        val stream = DataOutputStream(messageData)
        stream.write(MessageType.CBYE.toString().toByteArray())

        val message = NetworkOutputMessage()
        message.writeData(messageData)

        return message
    }
}