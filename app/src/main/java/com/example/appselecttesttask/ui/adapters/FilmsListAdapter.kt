package com.example.appselecttesttask.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appselecttesttask.R
import com.example.appselecttesttask.entity.FilmData
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer

class FilmsListAdapter: ListAdapter<FilmData, FilmsListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<FilmData>() {
        override fun areItemsTheSame(oldItem: FilmData, newItem: FilmData): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: FilmData, newItem: FilmData): Boolean =
            oldItem == newItem
    }
) {
    companion object {
        const val PAGE_SIZE = 5
    }

    private var pageNum = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_film_item, parent, false)
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = getItem(position)
        holder.run {
            val title = containerView.findViewById<TextView>(R.id.film_title)
            val description = containerView.findViewById<TextView>(R.id.film_description)
            val image = containerView.findViewById<ImageView>(R.id.film_image)

            title.text = "${position + 1 + pageNum * PAGE_SIZE}. ${film.title}"
            description.text = film.description
            Picasso.get().load(film.url).into(image)
        }
    }

    fun setPage(list: List<FilmData>, page: Int) {
        pageNum = page
        val sublist = list.subList(PAGE_SIZE * page, PAGE_SIZE * (page + 1))
        submitList(sublist)
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer
}