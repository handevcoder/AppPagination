package com.example.apppagination.model

import com.google.gson.annotations.SerializedName

data class ModelUser(

	@field:SerializedName("per_page")
	val perPage: Int,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int
)
data class DataItem(
	@field:SerializedName("total_pages")
	val totalPages: Int
)
