package com.example.chat_aplication.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.Base.OnDialogClickListner

abstract class BaseActivity<VB : ViewDataBinding, Vm : BaseViewModel<*>> : AppCompatActivity(),
    BaseNavigator {
    lateinit var viewBinding: VB
    lateinit var viewModel: Vm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = getViewModelId()

    }


    abstract fun getLayoutId(): Int
    abstract fun getViewModelId(): Vm


    fun showpass(editText: EditText, eye: ImageView, hide: ImageView) {
        eye.setOnClickListener {
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            eye.isVisible = false
            hide.isVisible = true
        }

    }

    fun hide(editText: EditText, hide: ImageView, eye: ImageView) {

        hide.setOnClickListener {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            hide.isVisible = false
            eye.isVisible = true
        }
    }

    var alertDialog: AlertDialog? = null
    var progressDialog: ProgressDialog? = null

    override fun showLoading(message: String) {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(message)
        progressDialog?.show()
    }

    override fun hideDialoge() {
        progressDialog?.dismiss()
        alertDialog?.dismiss()
        progressDialog = null
    }

    override fun showMessage(
        message: String,
        posActionTitle: String?,
        posAction: OnDialogClickListner?,
        negActionTitle: String?,
        negAction: OnDialogClickListner?,
    ) {
        val builder =
            AlertDialog.Builder(this)
                .setMessage(message)
        if (posActionTitle != null) {
            builder.setPositiveButton(posActionTitle) { DialogInterface, i ->
                DialogInterface.dismiss()
                posAction?.onClick()
            }}
            if (negActionTitle != null) {
                builder.setPositiveButton(posActionTitle) { DialogInterface, i ->
                    DialogInterface.dismiss()
                   negAction?.onClick()
                }
        }
        builder.show()

    }


}

interface BaseNavigator {
    fun showLoading(message: String)
    fun hideDialoge()
    fun showMessage(
        message: String,
        posActionTitle: String? = null,
        posAction: OnDialogClickListner? = null,
        negActionTitle: String? = null,
        negAction: OnDialogClickListner? = null,
    )

}

open class BaseViewModel<N : BaseNavigator> : ViewModel() {
    var navigator: N? = null
}
