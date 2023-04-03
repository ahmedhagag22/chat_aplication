package com.example.chat_aplication.dataBase.models

import android.os.Parcelable
import com.example.chat_aplication.ui.room.RoomCategories
import kotlinx.parcelize.Parcelize


@Parcelize
data class Room(
    var id:String?=null,
    var title:String?=null,
    var categoryId:String?=null,
    var createdBy:String?=null
):Parcelable
{
  fun  getImageId():Int
    {
return RoomCategories.getCategoryById(categoryId)
    .ImageId
    }
}
