package com.example.apppagination.pagination.adapter

import com.example.apppagination.pagination.data.State

class ListFooterViewHolder ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(status: State?) {
            itemView.progress_bar.visibility = if (status == LOADING) VISIBLE else INVISIBLE
            itemView.txt_error.visibility = if (status == ERROR) VISIBLE else INVISIBLE
        }

        companion object {
            fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_footer, parent, false)
                view.txt_error.setOnClickListener { retry() }
                return ListFooterViewHolder(view)
            }
        }

}