package com.gyoung.movierecord.function

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.gyoung.movierecord.R
import kotlinx.android.synthetic.main.custom_toast.view.*
fun Context.showCustomToast(message : String){
    val inflater: LayoutInflater = LayoutInflater.from(this)
    val toast_view : View = inflater.inflate(R.layout.custom_toast, null)

    toast_view.toast_message.text=message
    val toast= Toast(this)
    toast.view=toast_view
    toast.duration = Toast.LENGTH_SHORT
    toast.show()
    toast.setGravity(Gravity.BOTTOM,0,300)
}
