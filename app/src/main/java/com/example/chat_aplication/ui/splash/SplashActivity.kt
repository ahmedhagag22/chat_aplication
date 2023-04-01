package com.example.chat_aplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_aplication.R
import com.example.chat_aplication.UserProvider
import com.example.chat_aplication.dataBase.FireStoreUtils
import com.example.chat_aplication.dataBase.models.users
import com.example.chat_aplication.ui.home.HomeActivity
import com.example.chat_aplication.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            chickAutoLogin()
        }, 2000)
    }

    fun chickAutoLogin() {
        var fb = FirebaseAuth.getInstance()
        if (fb.currentUser == null) {
            goToLogin()
            return
        }
        FireStoreUtils().getUserFromDataBasse(fb.currentUser?.uid!!)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    goToLogin()
                    return@addOnCompleteListener
                }
                val user = it.result.toObject(users::class.java)
                UserProvider.user = user
                goToHome()

            }

    }

    fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
