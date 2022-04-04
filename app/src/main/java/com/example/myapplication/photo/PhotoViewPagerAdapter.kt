package com.example.myapplication.photo

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.photo.interfaces.OnScreenListener
import com.example.myapplication.photo.interfaces.PhotoViewCallback
import com.example.myapplication.photo.views.PhotoView

class PhotoViewPagerAdapter(
    private val data: ArrayList<Int>,
    private val photoViewCallback: PhotoViewCallback
) : PagerAdapter() {

    private var mFullscreen: Boolean = true

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = data.size

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        Log.d(PhotoView.TAG, "destroyItem pos:$position")
        if (view is PhotoView) {
            photoViewCallback.removeScreenListener(position)
            container.removeView(view)
            view.clear()
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.d(PhotoView.TAG, "instantiateItem pos:$position")
        val view = PhotoView(container.context).let {
            it.setMaxInitialScale(1f)
            it.setFullScreen(mFullscreen, false)
            it.enableImageTransforms(true)
            it
        }
        photoViewCallback.addScreenListener(position, object : OnScreenListener {
            override fun onFullScreenChanged(fullScreen: Boolean) {
                mFullscreen = fullScreen
            }

            override fun onViewActivated() {
                Log.d(PhotoView.TAG, "onViewActivated pos:$position")
                view.resetTransformations()
            }

            override fun onViewUpNext() {
                Log.d(PhotoView.TAG, "onViewUpNext pos:$position")
                view.resetTransformations()
            }

            override fun onInterceptMoveLeft(origX: Float, origY: Float): Boolean {
                return view.interceptMoveLeft(origX, origY)
            }

            override fun onInterceptMoveRight(origX: Float, origY: Float): Boolean {
                return view.interceptMoveRight(origX, origY)
            }

        })
        view.bindDrawable(ContextCompat.getDrawable(container.context, data[position]))
        container.addView(
            view,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        return view
    }
}