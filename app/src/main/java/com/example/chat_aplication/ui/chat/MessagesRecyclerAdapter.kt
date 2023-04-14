package com.example.chat_aplication.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_aplication.R
import com.example.chat_aplication.UserProvider
import com.example.chat_aplication.dataBase.models.Messages
import com.example.chat_aplication.databinding.ItemRecivedMessagesBinding
import com.example.chat_aplication.databinding.ItemSendMessagesBinding

class MessagesRecyclerAdapter(var messages: MutableList<Messages>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class MessageType(val value: Int) {
        received(1),
        sent(2)
    }


    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        if (message.senderId == UserProvider.user?.id) {
            return MessageType.sent.value
        }
        return MessageType.received.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MessageType.sent.value) {
            val viewBinding: ItemSendMessagesBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_send_messages, parent, false
                )
            return SendViewHolder(viewBinding)

        }

        val viewBinding: ItemRecivedMessagesBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recived_messages, parent, false
            )
        return ReceivedViewHolder(viewBinding)


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SendViewHolder) {
            holder.bind(messages[position])

        }
        if (holder is ReceivedViewHolder) {
            holder.bind(messages[position])

        }

    }

    override fun getItemCount(): Int = messages.size

    fun addMessages(message: Messages) {
        messages.add(message)
        notifyItemInserted(messages.size)
    }

    class SendViewHolder(var viewBinding: ItemSendMessagesBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(messages: Messages) {
            viewBinding.messages = messages
            viewBinding.invalidateAll()
        }
    }

    class ReceivedViewHolder(var viewBinding: ItemRecivedMessagesBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(messages: Messages) {
            viewBinding.messages = messages
            viewBinding.invalidateAll()
        }
    }
}
