package com.example.apppagination.pagination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppagination.R

class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(users: Users?) {
        binding.tvFullname.text = user.firstName + " " +user.lastName
        binding.tvEmail.text = user.email
        Glide.with(binding.root).load(user.avatar).circleCrop()
            .into(binding.ivImage)
    }

    companion object {
        fun create(parent: ViewGroup): UsersViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
            return UsersViewHolder(view)
        }
    }
}