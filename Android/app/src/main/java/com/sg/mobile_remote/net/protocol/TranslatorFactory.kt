package com.sg.mobile_remote.net.protocol

class TranslatorFactory() {
    private val _map = mapOf<MessageType, MessageTranslator>(
        MessageType.Synergy to HelloTranslator()
    )
    fun createTranslator(type : String): MessageTranslator? {
        return _map[MessageType.valueOf(type)]
    }
}