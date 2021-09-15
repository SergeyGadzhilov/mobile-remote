package com.sg.mobile_remote.commands

import com.sg.mobile_remote.ServiceClient

class Connect() : Command() {
    override fun run() {
        ServiceClient.connect()
    }
}