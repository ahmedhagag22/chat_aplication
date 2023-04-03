package com.example.chat_aplication.dataBase.models

import com.google.firebase.Timestamp

data class Messages(
    var id:String?=null,
    var content:String?=null,
    var roomId:String?=null,
    var senderId:String?=null,
    var senderName:String?=null,
    var dateTime:Timestamp?=null,
)
