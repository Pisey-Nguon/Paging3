package com.example.paging3

data class UserResponse(
    val page: Long,
    val perPage: Long,
    val total: Long,
    val totalPages: Long,
    val data: List<UserData>,
    val support: Support,
)

data class UserData(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
)

data class Support(
    val url: String,
    val text: String,
)

