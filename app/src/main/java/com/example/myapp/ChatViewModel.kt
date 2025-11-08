package com.example.myapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.UUID

class ChatViewModel : ViewModel() {

    data class Message(
        val id: String = UUID.randomUUID().toString(),
        val senderID: String,
        val text: String
    )

    private val _messages = mutableStateListOf<Message>()

    val messages: List<Message> get() = _messages

    fun addMessage(sender: String, message: String){
        _messages.add(Message(senderID = sender, text = message))
    }

}