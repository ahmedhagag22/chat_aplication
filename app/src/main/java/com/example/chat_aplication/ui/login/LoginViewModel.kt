package com.example.chat_aplication.ui.login

import androidx.databinding.ObservableField
import com.example.chat_aplication.ui.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : BaseViewModel<LoginNavigator>() {
    var email = ObservableField<String>()
    var errorEmail = ObservableField<String?>()
    var password = ObservableField<String>()
    var errorPassword = ObservableField<String?>()
    var auth = FirebaseAuth.getInstance()

    fun register() {
        if (!validateForm())
            return;
        navigator?.showLoading("Loading....")
        auth.signInWithEmailAndPassword(
            email.get()!!,
            password.get()!!
        ).addOnCompleteListener {
            navigator?.hideDialoge()
            if (it.isSuccessful) {
                navigator?.goToHome()
                return@addOnCompleteListener
            }
            navigator?.showMessage(it.exception?.localizedMessage ?: "")

        }


    }

    fun validateForm(): Boolean {
        var isValid = true

        if (email.get().isNullOrBlank()) {
            isValid = false
            //show error
            errorEmail.set("please enter your Email")
        } else {
            isValid = true
            errorEmail.set(null)
        }

        if (password.get().isNullOrBlank()) {
            isValid = false
            //show error
            errorPassword.set("please enter your password")
        } else {
            isValid = true
            //hide error
            errorPassword.set(null)
        }
        return isValid
    }

    fun goToRegister() {
        navigator?.goToRegister()
    }
    fun gotoPassword()
    {
        navigator?.gotoPassword()
    }

}