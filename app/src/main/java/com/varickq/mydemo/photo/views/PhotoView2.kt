package com.varickq.mydemo.photo.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

class PhotoView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : PhotoView(context, attrs, defStyleAttr) {
    companion object {
        const val TAG = "PhotoView2"
    }

    private var startX = 0f
    private var startY = 0f
    private var lastX = 0f
    private var firstItem = false
    private var lastItem = false

    fun setFirst(first: Boolean) {
        firstItem = first
    }

    fun setLast(last: Boolean) {
        lastItem = last
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                startX = event.x
                startY = event.y
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val interceptLeft = interceptMoveLeft(startX, startY)
                val interceptRight = interceptMoveRight(startX, startY)

                val x = event.x
                if (interceptLeft && interceptRight) {
                    println("$TAG dispatchTouchEvent -->> at middle")
                    // at middle
                    parent.requestDisallowInterceptTouchEvent(true)
                } else if (interceptLeft && ((x > lastX) || lastItem)) {
                    println("$TAG dispatchTouchEvent -->> at right edge")
                    // at right-edge, allow move to left
                    parent.requestDisallowInterceptTouchEvent(true)
                } else if (interceptRight && ((x < lastX) || firstItem)) {
                    println("$TAG dispatchTouchEvent -->> at left edge")
                    // at left-edge, allow move to right, except the first item.
                    parent.requestDisallowInterceptTouchEvent(true)
                } else {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
                lastX = x
            }
        }
        return super.dispatchTouchEvent(event)
    }
}