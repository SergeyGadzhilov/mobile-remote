package com.sg.mobile_remote.net.protocol

import android.util.Log
import com.sg.mobile_remote.net.NetworkInputMessage
import java.io.DataInputStream
import java.lang.IllegalArgumentException

class ProtocolMessage (private val _message : NetworkInputMessage) : DataInputStream(_message){
    private var _size = readInt()
    private val _type = readType()

    fun getType() : MessageType {
        return _type
    }

    private fun readType() : MessageType {
        var typeString = readString(4)

        if (typeString == "Syne") {
            typeString += readString(3)
        }

        var type = MessageType.None
        try {
            type = MessageType.valueOf(typeString)
        }
        catch (e: IllegalArgumentException) {
            val data = ByteArray(_size - typeString.length)
            read(data)
            Log.i("SGADTRACE", "Unknown message: $typeString")
            Log.i("SGADTRACE", "Message size: $_size")
            Log.i("SGADTRACE", "Message data: ${data.toString()}")
        }

        return type
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