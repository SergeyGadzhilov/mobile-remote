package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.Event
import com.sg.mobile_remote.net.NetworkMessage
import java.io.DataInputStream

class NetworkProtocol {
    private val _translatorsFactory = TranslatorFactory()

    fun createEvent(message: NetworkMessage) : Event {
        val type = getType(message.toDataInputStream())
        val translator = _translatorsFactory.createTranslator(type)
        return translator?.createEvent(message)!!
    }

    private fun getType(message: DataInputStream) : String {
        var type = ""

        message.skipBytes(4)
        while (message.available() > 0) {
            val value = message.read()
            if (value.toChar().isLetter()) {
                type += value.toChar()
            }
            else {
                break
            }
        }

        return type
    }
}