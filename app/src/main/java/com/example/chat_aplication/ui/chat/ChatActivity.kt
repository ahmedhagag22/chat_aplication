package com.example.chat_aplication.ui.chat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_aplication.Constant
import com.example.chat_aplication.R
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.databinding.ActivityChatBinding
import com.example.chat_aplication.ui.BaseActivity
import com.example.chat_aplication.ui.home.HomeActivity

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>(), NavigatorChat {
     var room: Room?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        initializeRoom()


    }
    // get data by put extra (parcelable)
    fun initializeRoom()
    {
        room = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constant.detailsRoom ,Room::class.java)
        } else {
            intent.getParcelableExtra(Constant.detailsRoom)
        }
        //passing room to VM for Usage
        viewModel.room=room
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun getViewModelId(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]
    }

    override fun finishAc() {
        var intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}