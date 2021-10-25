package com.sg.mobile_remote.net

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream

class NetworkMessage : ByteArrayOutputStream(){

    fun toDataInputStream(): DataInputStream {
        val byteStream = ByteArrayInputStream(toByteArray())
        return DataInputStream(byteStream)
    }
}