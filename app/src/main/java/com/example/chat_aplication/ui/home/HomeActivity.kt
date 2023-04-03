package com.example.chat_aplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_aplication.Constant
import com.example.chat_aplication.R
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.databinding.ActivityHomeBinding
import com.example.chat_aplication.ui.BaseActivity
import com.example.chat_aplication.ui.bottomSheet.JoinRoomFragment
import com.example.chat_aplication.ui.chat.ChatActivity
import com.example.chat_aplication.ui.room.AddRoamActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), Navigator_Home {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        initializeAdapter()
        subscribToLiveData()

    }

    fun subscribToLiveData() {
        viewModel.roomsLiveData.observe(this) {
            adapter.changeData(it)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadRooms()
    }


    var adapter = RoomsAdapter()
    fun initializeAdapter() {
        viewBinding.contentHome.recyclerRoom.adapter = adapter
        adapter.onItemClickListener = object : RoomsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, items: Room) {

                var intent = Intent(this@HomeActivity, ChatActivity::class.java)
                intent.putExtra(Constant.detailsRoom, items)
                startActivity(intent) }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home

    }

    override fun getViewModelId(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun goToAddRom() {
        var intent = Intent(this, AddRoamActivity::class.java)
        startActivity(intent)
    }
//    private fun showAddTaskBottomSheet() {
//        var addTaskFragment=JoinRoomFragment()
//        addTaskFragment.
//        }
//        //بياخد مني فرجمنت منجر ونل
//        addTaskFragment.show(supportFragmentManager,null)
//    }
}