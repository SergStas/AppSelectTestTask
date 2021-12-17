package com.example.appselecttesttask.presentation

import androidx.appcompat.app.AppCompatActivity
import com.example.appselecttesttask.entity.FilmData

interface IFilmsListContract {
    interface IView {
        fun displayList(films: List<FilmData>)

        fun showLoading(display: Boolean)

        fun showError(text: String, display: Boolean)
    }

    interface IPresenter {
        fun bind(activity: AbstractFilmsListActivity)

        fun reloadList()
    }

    abstract class AbstractFilmsListActivity: AppCompatActivity(), IView
}