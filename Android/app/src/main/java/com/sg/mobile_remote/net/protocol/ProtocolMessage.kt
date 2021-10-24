package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.net.NetworkMessage
import java.io.DataInputStream

class ProtocolMessage (private val _message : NetworkMessage) : DataInputStream(_message.toDataInputStream()){
    private val _size = readInt()
    private val _type = readType()

    fun getType() : MessageType {
        return _type
    }

    private fun readType() : MessageType {
        var type = readString(4)

        if (type == "Syne") {
            type += readString(3)
        }

        return MessageType.valueOf(type)
    }

    fun getSize() : Int {
        return _size
    }

    private fun readString(size : Int) : String {
        val data = ByteArray(size)
        read(data, 0, data.size)
        return String(data)
    }
}