package com.sg.mobile_remote.commands

import com.sg.mobile_remote.ServiceBinder

class Connect() : Command() {
    override fun run() {
        ServiceBinder.getService()?.connect()
    }
}