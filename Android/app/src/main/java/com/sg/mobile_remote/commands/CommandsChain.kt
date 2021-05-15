package com.sg.mobile_remote.commands

open class CommandsChain {
    private var next : Command? = null

    fun setNext(command: Command) {
        next = command
    }

    fun next() {
        next?.run()
    }
}