package com.example.chat_aplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_aplication.R
import com.example.chat_aplication.databinding.ActivityHomeBinding

import com.example.chat_aplication.ui.BaseActivity
import com.example.chat_aplication.ui.room.AddRoamActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), Navigator_Home {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        initializeAdapter()
        subscribToLiveData()

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadRooms()
    }
    fun subscribToLiveData()
    {
        viewModel.roomsLiveData.observe(this){
            adapter.changeData(it)
        }
    }

    var adapter=RoomsAdapter(null)
    fun initializeAdapter()
    {
      viewBinding.contentHome.recyclerRoom.adapter=adapter
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
}