package com.example.apppagination.pagination.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.apppagination.pagination.data.NetworkService
import com.example.apppagination.pagination.data.State
import com.example.apppagination.pagination.data.UsersDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class UsersListViewModel : ViewModel() {

        private val networkService = NetworkService.getService()
        var usersList: LiveData<PagedList<Users>>
        private val compositeDisposable = CompositeDisposable()
        private val pageSize = 5
        private val usersDataSourceFactory: UsersDataSourceFactory

        init {
            usersDataSourceFactory = UsersDataSourceFactory(compositeDisposable, networkService)
            val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
            usersList = LivePagedListBuilder(usersDataSourceFactory, config).build()
        }


        fun getState(): LiveData<State> = Transformations.switchMap(
            usersDataSourceFactory.usersDataSourceLiveData,
            UsersDataSource::state
        )

        fun retry() {
            usersDataSourceFactory.usersDataSourceLiveData.value?.retry()
        }

        fun listIsEmpty(): Boolean {
            return usersList.value?.isEmpty() ?: true
        }

        override fun onCleared() {
            super.onCleared()
            compositeDisposable.dispose()
        }
    }
}