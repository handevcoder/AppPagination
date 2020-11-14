package com.example.apppagination.pagination.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.apppagination.pagination.viewModel.UsersListViewModel

class MainmainActivity : AppCompatActivity() {

    private lateinit var viewModel: UsersListViewModel
    private lateinit var usersListAdapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        viewModel = ViewModelProvider(this).get(UsersListViewModel::class.java)
        initAdapter()
        initState()
    }

    private fun initAdapter() {
        usersListAdapter = UsersListAdapter { viewModel.retry() }
        recycler_view.adapter = usersListAdapter
        viewModel.usersList.observe(this,
            Observer {
                usersListAdapter.submitList(it)
            })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == LOADING) VISIBLE else GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == ERROR) VISIBLE else GONE
            if (!viewModel.listIsEmpty()) {
                usersListAdapter.setState(state ?: State.DONE)
            }
        })
    }

}