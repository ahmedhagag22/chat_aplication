package com.example.chat_aplication.ui.chat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat_aplication.Constant
import com.example.chat_aplication.R
import com.example.chat_aplication.dataBase.FireStoreUtils
import com.example.chat_aplication.dataBase.models.Messages
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.databinding.ActivityChatBinding
import com.example.chat_aplication.ui.BaseActivity
import com.example.chat_aplication.ui.home.HomeActivity
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ListenerRegistration

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>(), NavigatorChat {
    var room: Room? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        initializeRoom()
        initializeMessagesAdapter()
        subscribeToMessagesChange()

    }

    val messagesAdapter = MessagesRecyclerAdapter(mutableListOf())
        lateinit var layoutManager: LinearLayoutManager
    fun initializeMessagesAdapter() {
        layoutManager=LinearLayoutManager(this)
        layoutManager.stackFromEnd=true
        viewBinding.contentChat.recyclerView.adapter = messagesAdapter
        viewBinding.contentChat.recyclerView.layoutManager=layoutManager
    }



    override fun onStart() {
        super.onStart()
        subscribeToMessagesChange()
    }
    //update messages (real time dataBase) lw ay 7ad b3t messages y7sal update (we ygeb kl el messages)
    var listener: ListenerRegistration? = null
    fun subscribeToMessagesChange() {
        if (listener == null) {
            listener = FireStoreUtils()
                .getRoomMessages(room?.id ?: "")
                .addSnapshotListener(
                    EventListener { value, error ->
                        if (error != null) {
                            showMessage(error.localizedMessage,
                                "try Again",
                                posAction = {
                                    subscribeToMessagesChange()
                                })
                            return@EventListener
                        }
                        value?.documentChanges
                            ?.forEach {
                                val message = it.document.toObject(Messages::class.java)
                                messagesAdapter.addMessages(message)
                             viewBinding.contentChat.recyclerView.smoothScrollToPosition(messagesAdapter.itemCount)
                            }
                    }
                )
        }
    }


    override fun onStop() {
        super.onStop()
        listener?.remove()
        listener = null
    }

    // get data by put extra (parcelable)
    fun initializeRoom() {
        room = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constant.detailsRoom, Room::class.java)
        } else {
            intent.getParcelableExtra(Constant.detailsRoom)
        }
        //passing room to VM for Usage
        viewModel.room = room
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun getViewModelId(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]
    }

    override fun finishAc() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}