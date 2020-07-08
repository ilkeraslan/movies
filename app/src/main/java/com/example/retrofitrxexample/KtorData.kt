package com.example.retrofitrxexample

data class Users(
    val users: List<User>
)

data class User(
    val userId: String,
    val firstName: String,
    val lastName: String
)