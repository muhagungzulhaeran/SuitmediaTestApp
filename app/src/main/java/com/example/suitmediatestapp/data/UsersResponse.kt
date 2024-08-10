package com.example.suitmediatestapp.data

import com.google.gson.annotations.SerializedName

data class UsersResponse(
	val perPage: Int? = null,
	val listUser: List<DataItem> = emptyList(),
	val page: Int? = null,
)

data class DataItem(
	val lastName: String? = null,
	val avatar: String? = null,
	val firstName: String? = null,
	val email: String? = null
)


