package com.example.myapp.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.UUID

class ChatViewModel : ViewModel() {

    sealed interface Message{
        val id: String
        val senderID: String
        val text: String
    }

    data class TextMessage(
        override val id: String = UUID.randomUUID().toString(),
        override val senderID: String,
        override val text: String
    ) : Message

    data class RequestMessage(
        override val id: String = UUID.randomUUID().toString(),
        override val senderID: String,
        override val text: String,
        val amount: Double
    ) : Message

    private val _messages = mutableStateListOf<Message>()

    val messages: List<Message> get() = _messages

    fun addTextMessage(sender: String, message: String){
        if (message.isBlank()) return
        _messages.add(TextMessage(senderID = sender, text = message))
    }

    fun addRequestMessage(sender: String, message: String, amount: Double){
        _messages.add(RequestMessage(senderID = sender, text = message, amount = amount))
    }

}