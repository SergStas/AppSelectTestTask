package com.example.appselecttesttask.ui

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appselecttesttask.R
import com.example.appselecttesttask.entity.FilmData
import com.example.appselecttesttask.presentation.FilmsListPresenter
import com.example.appselecttesttask.presentation.IFilmsListContract
import com.example.appselecttesttask.ui.adapters.FilmsListAdapter
import com.example.appselecttesttask.ui.adapters.PaginationBarAdapter
import kotlinx.android.synthetic.main.activity_main.*

class FilmsListActivity : IFilmsListContract.AbstractFilmsListActivity() {
    private var presenter: IFilmsListContract.IPresenter = FilmsListPresenter()
    private val contentAdapter = FilmsListAdapter()
    private val paginationBarAdapter = PaginationBarAdapter(::setPage)
    private var films = emptyList<FilmData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppSelectTestTask)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = FilmsListPresenter()
        presenter.bind(this)

        setListAdapters()

        b_reload.setOnClickListener {
            presenter.reloadList()
        }
    }

    override fun displayList(films: List<FilmData>) {
        val pagesCount = films.size / FilmsListAdapter.PAGE_SIZE
        val numbersList = (1 .. pagesCount).toList()
        this.films = films
        paginationBarAdapter.submitList(numbersList)
        setPage(0)
    }

    override fun showLoading(display: Boolean) {
        pb.isVisible = display
    }

    override fun showError(text: String, display: Boolean) {
        error_message.text = text
        error_message.isVisible = display
        b_reload.isVisible = display
    }

    private fun setListAdapters() {
        rv_content.let {
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false,
            )
            it.adapter = this.contentAdapter
        }

        rv_pages.let {
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false,
            )
            it.adapter = this.paginationBarAdapter
        }
    }

    private fun setPage(num: Int) {
        contentAdapter.setPage(films, num)
    }
}