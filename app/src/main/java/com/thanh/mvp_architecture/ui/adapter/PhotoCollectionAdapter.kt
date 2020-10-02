package com.thanh.mvp_architecture.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.thanh.mvp_architecture.R
import com.thanh.mvp_architecture.model.PhotoModel

class PhotoCollectionAdapter(private val context: Context, private val mData: List<PhotoModel>) :
        RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType < mData.size) {
            ViewHolderPhotoItem(LayoutInflater.from(parent.context).inflate(R.layout.item_rcv_photo, parent, false))
        } else ViewHolderPhotoItem(
                LayoutInflater.from(parent.context).inflate(R.layout.item_rcv_loadmore, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder: ViewHolderPhotoItem
        if (position < mData.size) {
            viewHolder = holder as ViewHolderPhotoItem
            viewHolder.tv_name.text = mData.get(position).name
            viewHolder.tv_desc.text = "They là mô tả"
            Picasso.get().load(mData[position].url).into(viewHolder.img)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mData.size + 1
    }

    inner class ViewHolderPhotoItem(itemView: View) : ViewHolder(itemView) {
        var tv_name: TextView
        var tv_desc: TextView
        var img: ImageView

        init {
            tv_desc = itemView.findViewById(R.id.tv_desc)
            tv_name = itemView.findViewById(R.id.tv_name)
            img = itemView.findViewById(R.id.img)
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, "item: " + adapterPosition, Toast.LENGTH_SHORT).show()
            })
        }
    }

    inner class ViewHolderLoadMore(view: View) : ViewHolder(view)
}