package com.example.chat_aplication.ui.chat

import androidx.databinding.ObservableField
import com.example.chat_aplication.UserProvider
import com.example.chat_aplication.dataBase.FireStoreUtils
import com.example.chat_aplication.dataBase.models.Messages
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.ui.BaseViewModel
import com.google.firebase.Timestamp

class ChatViewModel : BaseViewModel<NavigatorChat>() {
    var room: Room? = null
    var messageFiled = ObservableField<String>()
    fun finishAc() {
        navigator?.finishAc()
    }

    fun sendMessage() {
        if (messageFiled.get().isNullOrBlank()) return
        val message = Messages(
            content = messageFiled.get(),
            senderName = UserProvider?.user?.userName,
            senderId = UserProvider?.user?.id,
            roomId = room?.id,
            dateTime = Timestamp.now()
        )
        FireStoreUtils()
            .sendMessage(message)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    messageFiled.set("")
                    return@addOnCompleteListener
                }
                navigator?.showMessage("error  sending your message ",
                    posActionTitle = "try again",
                    posAction = { sendMessage() })

            }
    }
}