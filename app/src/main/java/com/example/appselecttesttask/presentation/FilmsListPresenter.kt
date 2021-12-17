package com.example.appselecttesttask.presentation

import com.example.appselecttesttask.R
import com.example.appselecttesttask.data.FilmsDataSource
import com.example.appselecttesttask.entity.FilmsListResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListPresenter: IFilmsListContract.IPresenter {
    private var activity: IFilmsListContract.AbstractFilmsListActivity? = null
    private val dao = FilmsDataSource()

    override fun bind(activity: IFilmsListContract.AbstractFilmsListActivity) {
        this.activity = activity

        loadList()
    }

    override fun reloadList() = loadList()

    private fun loadList() {
        activity?.showError("", false)
        activity?.showLoading(true)

        runBlocking {
            launch {
                val call = dao.getFilmsCall()
                val callback = object : Callback<FilmsListResponse> {
                    override fun onResponse(
                        call: Call<FilmsListResponse>,
                        response: Response<FilmsListResponse>,
                    ) {
                        activity?.displayList(response.body()?.films ?: emptyList())
                        activity?.showLoading(false)
                    }

                    override fun onFailure(call: Call<FilmsListResponse>, t: Throwable) {
                        activity?.showError(activity!!.getString(R.string.em_loading_failed), true)
                        activity?.showLoading(false)
                    }
                }
                call?.enqueue(callback)
            }
        }
    }
}