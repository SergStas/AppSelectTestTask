package com.example.appselecttesttask.ui.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appselecttesttask.R
import kotlinx.android.extensions.LayoutContainer

class PaginationBarAdapter(val onClick: (Int) -> Unit): ListAdapter<Int, PaginationBarAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem == newItem
    }
) {
    private var prevItem : TextView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_page_num, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val num = getItem(position)
        val textView = holder.containerView.findViewById<TextView>(R.id.page_num_value)
        textView.text = num.toString()

        textView.setOnClickListener {
            onClick(position)
            if (prevItem != null) {
                prevItem?.setTextColor(Color.BLACK)
            }
            textView.setTextColor(Color.RED)
            prevItem = textView
        }
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer
}