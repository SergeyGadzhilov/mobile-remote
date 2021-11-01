package com.sg.mobile_remote.net

import java.io.*

class NetworkInputMessage(private val _stream: InputStream?) : DataInputStream(_stream) {
}

class NetworkOutputMessage(private val _stream: ByteArrayOutputStream = ByteArrayOutputStream()) :
    DataOutputStream(_stream)
{
    fun toByteArray() : ByteArray {
        return _stream.toByteArray()
    }

    fun writeData(data : ByteArrayOutputStream) {
        writeInt(data.size())
        data.writeTo(this)
    }

    override fun toString(): String {
        return _stream.toString()
    }
}