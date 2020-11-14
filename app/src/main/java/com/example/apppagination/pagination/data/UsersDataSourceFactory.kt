package com.example.apppagination.pagination.data

class UsersDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService)
    : DataSource.Factory<Int, Users>() {

    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Int, Users> {
        val newsDataSource = UsersDataSource(networkService, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }
}