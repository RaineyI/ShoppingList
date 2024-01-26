package com.raineyi.shoppinglist.presentation

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.raineyi.shoppinglist.R

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, error: Boolean){
    val message = if (error) {
        textInputLayout.context.getString(R.string.error_input_name)
        } else {
            null
        }
    textInputLayout.error = message
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout, error: Boolean){
    val message = if (error) {
        textInputLayout.context.getString(R.string.error_input_count)
    } else {
        null
    }
    textInputLayout.error = message
}
