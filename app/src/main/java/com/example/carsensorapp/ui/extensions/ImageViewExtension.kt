package com.example.carsensorapp.ui.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageViewExtension {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun ImageView.loadImage(imageUrl: String?) {
            if (imageUrl == null) {
                return
            }

            Glide.with(this).load(imageUrl).into(this)
        }
    }
}
