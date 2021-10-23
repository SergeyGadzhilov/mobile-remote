package com.sg.mobile_remote.net

import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.util.*

class NetworkMessage {
    private val _data = Vector<Int>()

    fun add(value: Int?) {
        _data.add(value)
    }

    fun toDataInputStream(): DataInputStream {
        val byteStream = ByteArrayInputStream(toByteArray())
        return DataInputStream(byteStream)
    }

    fun toByteArray() : ByteArray {
        val result = ByteArray(_data.size)

        for (i in 0 until _data.size) {
            result[i] = _data[i].toByte()
        }

        return result
    }

    override fun toString(): String {
        return _data.toString()
    }

    fun clear() {
        _data.clear()
    }

}