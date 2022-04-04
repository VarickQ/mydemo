package com.example.myapplication.photo

import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.photo.interfaces.OnScreenListener
import com.example.myapplication.photo.interfaces.PhotoViewCallback
import com.example.myapplication.photo.views.PhotoView
import java.util.*

class PhotoViewController : PhotoViewPager.OnInterceptTouchListener, PhotoViewCallback,
    ViewPager.OnPageChangeListener {

    private val mScreenListeners: HashMap<Int, OnScreenListener> = HashMap()
    private var mCurrentPhotoIndex: Int = 0

    override fun onTouchIntercept(origX: Float, origY: Float): PhotoViewPager.InterceptType {
        var interceptLeft = false
        var interceptRight = false

        Log.d(PhotoView.TAG, "onTouchIntercept mScreenListeners size:${mScreenListeners.size}")
        mScreenListeners.values.forEach {
            if (!interceptLeft) {
                interceptLeft = it.onInterceptMoveLeft(origX, origY)
            }
            if (!interceptRight) {
                interceptRight = it.onInterceptMoveRight(origX, origY)
            }
        }

        Log.d(PhotoView.TAG, "onTouchIntercept left:$interceptLeft right:$interceptRight")

        if (interceptLeft) {
            return if (interceptRight) {
                PhotoViewPager.InterceptType.BOTH
            } else PhotoViewPager.InterceptType.LEFT
        } else if (interceptRight) {
            return PhotoViewPager.InterceptType.RIGHT
        }
        return PhotoViewPager.InterceptType.NONE
    }

    override fun addScreenListener(position: Int, listener: OnScreenListener) {
        Log.d(PhotoView.TAG, "addScrollableListener pos:$position")
        mScreenListeners[position] = listener
    }

    override fun removeScreenListener(position: Int) {
        Log.d(PhotoView.TAG, "removeScreenListener pos:$position")
        mScreenListeners.remove(position)
    }

    override fun setViewActivated(position: Int) {
        Log.d(PhotoView.TAG, "setViewActivated pos:$position")
        val listener = mScreenListeners[position]
        listener?.onViewActivated()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.d(PhotoView.TAG, "onPageScrolled pos:$position positionOffset:$positionOffset")
        if (positionOffset < 0.0001) {
            val before = mScreenListeners[position - 1]
            before?.onViewUpNext()
            val after = mScreenListeners[position + 1]
            after?.onViewUpNext()
        }
    }

    override fun onPageSelected(position: Int) {
        Log.d(PhotoView.TAG, "onPageSelected pos:$position")
        mCurrentPhotoIndex = position
        setViewActivated(position)
    }
}
