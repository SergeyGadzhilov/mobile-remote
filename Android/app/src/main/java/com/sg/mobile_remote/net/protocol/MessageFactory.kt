package com.sg.mobile_remote.net.protocol

import com.sg.mobile_remote.core.events.EventType
import com.sg.mobile_remote.net.protocol.messages.CALV
import com.sg.mobile_remote.net.protocol.messages.CBYE
import com.sg.mobile_remote.net.protocol.messages.MessageHello
import com.sg.mobile_remote.net.protocol.messages.QINF

class MessageFactory() {
    private val _mapTranslators = mapOf<MessageType, MessageTranslator>(
        MessageType.Synergy to MessageHello(),
        MessageType.QINF to QINF(),
        MessageType.CALV to CALV(),
        MessageType.CBYE to CBYE()
    )

    private val _mapEvents = mapOf<EventType, MessageType> (
        EventType.Hello to MessageType.Synergy,
        EventType.QueryInfo to MessageType.QINF,
        EventType.KeepAlive to MessageType.CALV,
        EventType.Bye to MessageType.CBYE
    )

    fun createTranslator(type : MessageType): MessageTranslator? {
        return _mapTranslators[type]
    }

    fun createTranslator(type : EventType) : MessageTranslator? {
        val messageType = _mapEvents[type]
        return _mapTranslators[messageType]
    }
}