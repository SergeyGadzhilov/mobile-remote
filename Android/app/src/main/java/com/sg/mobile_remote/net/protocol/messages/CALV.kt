package com.sg.mobile_remote.net.protocol.messages

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.core.events.EventKeepAlive
import com.sg.mobile_remote.net.NetworkOutputMessage
import com.sg.mobile_remote.net.protocol.MessageTranslator
import com.sg.mobile_remote.net.protocol.MessageType
import com.sg.mobile_remote.net.protocol.ProtocolMessage
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class CALV : MessageTranslator {
    override fun createEvent(message: ProtocolMessage): Event {
       return EventKeepAlive()
    }

    override fun createNetworkMessage(event: Event): NetworkOutputMessage {
        val messageData = ByteArrayOutputStream()
        val stream = DataOutputStream(messageData)
        stream.write(MessageType.CALV.toString().toByteArray())

        val message = NetworkOutputMessage()
        message.writeData(messageData)

        return message
    }
}