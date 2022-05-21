package com.varickq.mydemo.photo.interfaces

interface PhotoViewCallback {

    fun addScreenListener(position: Int, listener: OnScreenListener)

    fun removeScreenListener(position: Int)

    fun setViewActivated(position: Int)
}