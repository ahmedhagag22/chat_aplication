package com.example.chat_aplication.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.example.chat_aplication.UserProvider
import com.example.chat_aplication.dataBase.FireStoreUtils
import com.example.chat_aplication.dataBase.models.Messages
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.ui.BaseViewModel
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
        try {
            FireStoreUtils()
                .sendMessage(message)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        messageFiled.set("")
                        return@addOnCompleteListener
                    }
        }
        }
        catch (e:Exception)
        {
            navigator?.showMessage("error  sending your message ",
                posActionTitle = "try again",
                posAction = { sendMessage() })
        }




    }
}}