package com.example.chat_aplication.dataBase

import com.example.chat_aplication.dataBase.models.Messages
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.dataBase.models.users
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FireStoreUtils {
    var db = FirebaseFirestore.getInstance()
    val userCollection: String = "Users"
    val roomsCollection: String = "Rooms"
    fun addUserToDataBase(users: users): Task<Void> {
        var docRef = db.collection(userCollection)
            .document(users.id!!)
        return docRef.set(users)
    }


    fun getUserFromDataBasse(uid: String): Task<DocumentSnapshot> {
        var docRef = db.collection(userCollection)
            .document(uid)
        return docRef.get()
    }

    fun getRoomCollection(): CollectionReference {
        val dataBase = FirebaseFirestore.getInstance()
        return dataBase.collection(roomsCollection)
    }

    fun insertRoomToDataBase(room: Room): Task<Void> {
        var docRef = getRoomCollection()
            .document()
        room.id = docRef.id
        return docRef.set(room)

    }

    fun getAllRoomsToDataBase(): Task<QuerySnapshot> {
        return getRoomCollection()
            .get()
    }

    fun getYourRoom(createdBy: String): Task<QuerySnapshot> {
        return getRoomCollection().document(createdBy)
            .collection("Rooms")
            .get()
    }


    fun sendMessage(message: Messages): Task<Void> {
        var roomRef = getRoomCollection()
            .document(message.roomId ?: "")
        var messages = roomRef.collection("messages")
        var messageDoc = messages.document()
        message.id = messageDoc.id
        return messageDoc.set(message)
    }

    fun getRoomMessages(roomId: String): Query{
        return getRoomCollection().document(roomId)
            .collection("messages")
            .orderBy("dateTime")
    }


}