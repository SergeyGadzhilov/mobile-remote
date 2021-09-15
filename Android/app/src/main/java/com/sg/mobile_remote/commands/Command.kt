package com.sg.mobile_remote.commands

abstract class Command {
    private var next : Command? = null

    abstract fun run()

    fun setNext(command: Command) : Command {
        if (next == null) {
            next = command
        }
        else {
            next?.setNext(command)
        }

        return this
    }

    fun next() {
        next?.run()
    }
}