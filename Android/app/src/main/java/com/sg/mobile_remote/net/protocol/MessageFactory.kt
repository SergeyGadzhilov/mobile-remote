package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.EventType

class MessageFactory() {
    private val _mapTranslators = mapOf<MessageType, MessageTranslator>(
        MessageType.Synergy to MessageHello()
    )

    private val _mapEvents = mapOf<EventType, MessageType> (
        EventType.Hello to MessageType.Synergy
    )

    fun createTranslator(type : MessageType): MessageTranslator? {
        return _mapTranslators[type]
    }

    fun createTranslator(type : EventType) : MessageTranslator? {
        val messageType = _mapEvents[type]
        return _mapTranslators[messageType]
    }
}