package com.example.myapplication.photo

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.photo.views.PhotoView

class PhotoViewPagerAdapter(private val data: ArrayList<Int>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = data.size

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        if (view is PhotoView) {
            container.removeView(view)
            view.clear()
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = PhotoView(container.context).let {
            it.setMaxInitialScale(1f)
            it.setFullScreen(true, false)
            it.enableImageTransforms(true)
            it
        }
        container.addView(
            view,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        view.bindDrawable(ContextCompat.getDrawable(container.context, data[position]))
        return view
    }
}