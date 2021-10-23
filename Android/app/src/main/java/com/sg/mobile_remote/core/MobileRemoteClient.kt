package com.sg.mobile_remote.core

import com.sg.mobile_remote.net.Network
import com.sg.mobile_remote.net.NetworkRouter
import com.sg.mobile_remote.net.protocol.NetworkProtocol

class MobileRemoteClient : Thread() {
    private val _networkRouter = NetworkRouter(Network(), NetworkProtocol())

    override fun run() {
        _networkRouter.connect("192.168.0.165", 24800)
    }
}