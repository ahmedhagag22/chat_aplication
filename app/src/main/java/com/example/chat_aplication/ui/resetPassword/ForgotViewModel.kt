package com.example.chat_aplication.ui.resetPassword

import androidx.databinding.ObservableField
import com.example.chat_aplication.ui.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class ForgotViewModel : BaseViewModel<ForgotNavigator>() {
    var email = ObservableField<String>()
    var errorEmail = ObservableField<String?>()
    var auth = FirebaseAuth.getInstance()

    fun forgotPassword() {
        if (!validateForm())
            return;
        navigator?.showLoading("Loading....")
        auth.sendPasswordResetEmail(email.get()!!)
            .addOnCompleteListener {
                navigator?.hideDialoge()
                if (it.isSuccessful) {
                    navigator?.showMessage("chick your Email ...")

                } else {
                    navigator?.showMessage(it.exception?.localizedMessage ?: "")
                }
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
        return isValid
    }
fun finshActivity()
{
    navigator?.finshActivity()
}

}