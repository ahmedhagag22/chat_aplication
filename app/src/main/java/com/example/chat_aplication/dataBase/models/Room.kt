package com.example.chat_aplication.dataBase.models

import com.example.chat_aplication.ui.room.RoomCategories

data class Room(
    var id:String?=null,
    var title:String?=null,
    var categoryId:String?=null,
    var createdBy:String?=null
)
{
  fun  getImageId():Int
    {
return RoomCategories.getCategoryById(categoryId)
    .ImageId
    }
}
