package com.testlib.mylibrary

import android.content.Context
import android.widget.Toast

data class MyToast(
    var context: Context? = null,
    var message: String? = "",
    var duration: Int = 0
)

fun MyToast.show() {
    Toast.makeText(context, message, duration).show()
}