package com.example.carsensorapp.ui.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageViewExtension {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
//        fun loadImage(view: ImageView, imageUrl: String) {
//            Glide.with(view).load("http://goo.gl/gEgYUd").into(view)
//        }
        fun ImageView.loadImage(imageUrl: String) {
            Glide.with(this).load(imageUrl).into(this)
        }
    }
}
