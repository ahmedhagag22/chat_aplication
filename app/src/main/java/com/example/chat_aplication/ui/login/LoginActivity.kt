package com.example.chat_aplication.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_aplication.R
import com.example.chat_aplication.databinding.ActivityLoginBinding
import com.example.chat_aplication.ui.BaseActivity
import com.example.chat_aplication.ui.home.HomeActivity
import com.example.chat_aplication.ui.register.RegisterActivity
import com.example.chat_aplication.ui.resetPassword.ForgetPasswordActivity


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelId(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this

        showpass(
            viewBinding.contentLogin.password,
            viewBinding.contentLogin.icon,
            viewBinding.contentLogin.icon2
        )
        hide(
            viewBinding.contentLogin.password,
            viewBinding.contentLogin.icon2,
            viewBinding.contentLogin.icon
        )
    }


    override fun goToHome() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun goToRegister() {
        var intetn = Intent(this, RegisterActivity::class.java)
        startActivity(intetn)
    }

    override fun gotoPassword() {
        var intetn = Intent(this,ForgetPasswordActivity::class.java)
        startActivity(intetn)
    }
}