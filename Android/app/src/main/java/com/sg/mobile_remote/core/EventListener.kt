package com.sg.mobile_remote.core

import com.sg.mobile_remote.core.events.Event

interface EventListener {
    fun handleEvent(event: Event)
}