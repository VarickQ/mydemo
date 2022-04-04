package com.example.myapplication.photo.interfaces

interface PhotoViewCallback {

    fun addScreenListener(position: Int, listener: OnScreenListener)

    fun removeScreenListener(position: Int)

    fun setViewActivated(position: Int)
}