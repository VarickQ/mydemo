package com.varickq.mydemo.photo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.varickq.mydemo.databinding.ItemPhotoViewHolderBinding

class PhotoViewPager2Adapter(private val mContext: Context, private val data: ArrayList<Int>) :
    RecyclerView.Adapter<PhotoViewPager2Adapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemPhotoViewHolderBinding.inflate(
                LayoutInflater.from(mContext),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.photoView2.apply {
            setMaxInitialScale(1f)
            setFullScreen(true, false)
            enableImageTransforms(true)
            setFirst(position == 0)
            setLast(position == itemCount - 1)
            bindDrawable(ContextCompat.getDrawable(mContext, data[position]))
        }
    }

    class ItemHolder(var binding: ItemPhotoViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}