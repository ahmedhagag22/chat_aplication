package com.example.chat_aplication.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.chat_aplication.dataBase.FireStoreUtils
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.ui.BaseViewModel

class HomeViewModel:BaseViewModel<Navigator_Home>() {
    val roomsLiveData=MutableLiveData<List<Room>>()
    fun gotoAddRom()
    {
        navigator?.goToAddRom()
    }
    fun loadRooms(){
        FireStoreUtils()
            .getAllRoomsToDataBase()
            .addOnCompleteListener {
                if (!it.isSuccessful)
                {
                    navigator?.showMessage("error to loading rooms")
                    return@addOnCompleteListener
                }
                var rooms=it.result.toObjects(Room::class.java)
                roomsLiveData.value=rooms
            }

    }
}