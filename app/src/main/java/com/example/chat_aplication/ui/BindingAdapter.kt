package com.example.chat_aplication.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun showErrorOnText(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
}

