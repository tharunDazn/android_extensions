package com.example.demoapplication.utils.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.demoapplication.utils.convertDpToPixel
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import java.io.FileOutputStream


fun ImageView.loadImage(url: String) {
    if (url.isEmpty()) return

    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .into(this)
}

fun ImageView.loadImage(drawableId: Int) {
    Glide.with(this)
        .load(drawableId)
        .into(this)
}

fun ImageView.loadImage(bitmap: Bitmap) {
    Glide.with(this)
        .load(bitmap)
        .into(this)
}

fun ImageView.loadCircleImage(url: String) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions.circleCropTransform()
        )
        .into(this)
}

fun ImageView.loadCircleImage(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .apply(
            RequestOptions.circleCropTransform()
        )
        .into(this)
}

fun ImageView.loadCircleImage(drawableId: Int) {
    Glide.with(this)
        .load(drawableId)
        .apply(
            RequestOptions.circleCropTransform()
        )
        .into(this)
}

fun ImageView.loadCircleImage(bitmap: Bitmap) {
    Glide.with(this)
        .load(bitmap)
        .apply(
            RequestOptions.circleCropTransform()
        )
        .into(this)
}

fun ImageView.loadRoundedImage(url: String, radius: Int = 10) {
    Glide.with(this)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}

fun ImageView.loadRoundedImage(uri: Uri, radius: Int = 10) {
    Glide.with(this)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}

fun ImageView.loadRoundedImage(drawableId: Int, radius: Int = 10) {
    Glide.with(this)
        .load(drawableId)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}

fun ImageView.loadRoundedImage(bitmap: Bitmap, radius: Int = 10) {
    Glide.with(this)
        .load(bitmap)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}

fun ShapeableImageView.setRadius(radius: Int) {
    shapeAppearanceModel = shapeAppearanceModel
        .toBuilder()
        .setAllCornerSizes(convertDpToPixel(radius.toFloat(), context))
        .build()
}


/** convert drawable to bitmap */
fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        return bitmap
    }

    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}

/** save bitmap to file */
fun Bitmap.saveFile(path: String) {
    val f = File(path)
    if (!f.exists()) {
        f.createNewFile()
    }
    val stream = FileOutputStream(f)
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    stream.flush()
    stream.close()
}