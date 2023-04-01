package com.example.chat_aplication.ui.room

import androidx.databinding.ObservableField
import com.example.chat_aplication.UserProvider
import com.example.chat_aplication.dataBase.FireStoreUtils
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.ui.BaseViewModel

class AddRomViewModel : BaseViewModel<AddRomNav>() {
    var selectedCategory: RoomCategories = RoomCategories.getCategory()[0]
    var roomName = ObservableField<String>()
    var description = ObservableField<String>()
    var errorRoomName = ObservableField<String?>()
    var errorDescription = ObservableField<String?>()
    fun create() {
        if (!validateForm()) return
        val room = Room(
            title = roomName.get(),
            categoryId = selectedCategory.id,
            createdBy = UserProvider.user?.id
        )
        navigator?.showLoading("Loading...")
        FireStoreUtils()
            .insertRoomToDataBase(room)
            .addOnCompleteListener {
                navigator?.hideDialoge()
                if (!it.isSuccessful) {
                    navigator?.showMessage(it.exception?.localizedMessage ?: "")
                    return@addOnCompleteListener
                }
                navigator?.showMessage(
                    "Room Created Successful",
                    "ok", posAction = { navigator?.goToHome() }
                )

            }

    }

    fun validateForm(): Boolean {
        var isValid = true
        if (roomName.get().isNullOrBlank()) {
            //show error
            isValid = false
            errorRoomName.set("please enter Room Name ")
        } else {
            //hide error
            errorRoomName.set(null)
        }
        if (description.get().isNullOrBlank()) {
            //show error
            isValid = false
            errorDescription.set("please enter Description ")
        } else {
            //hide error
            errorDescription.set(null)
        }
        return isValid
    }

    fun back() {
        navigator?.backToHome()
    }
}
