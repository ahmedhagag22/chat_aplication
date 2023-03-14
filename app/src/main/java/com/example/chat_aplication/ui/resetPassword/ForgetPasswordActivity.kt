package com.example.chat_aplication.ui.resetPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_aplication.R
import com.example.chat_aplication.databinding.ActivityForgetPasswordBinding
import com.example.chat_aplication.ui.BaseActivity

class ForgetPasswordActivity : BaseActivity<ActivityForgetPasswordBinding,ForgotViewModel>(),ForgotNavigator {

    override fun getLayoutId(): Int {
       return R.layout.activity_forget_password
    }

    override fun getViewModelId(): ForgotViewModel {
       return ViewModelProvider(this).get(ForgotViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this
    }

    override fun finshActivity() {
        finish()
    }


}