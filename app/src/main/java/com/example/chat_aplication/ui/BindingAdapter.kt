package com.example.chat_aplication.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun showErrorOnText(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
}

@BindingAdapter("textId")
fun bindStringInTextViewUsingId(textView: TextView, titleId: String) {
    textView.setText(titleId)

}

@BindingAdapter("imageId")
fun bindImageUsingImageId(imageView: ImageView, imageId: Int) {

    imageView.setImageResource(imageId)
}

