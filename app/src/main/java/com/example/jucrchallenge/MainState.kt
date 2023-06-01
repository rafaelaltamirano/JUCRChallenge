package com.example.jucrchallenge

import com.example.jucrchallenge.domain.entities.User

data class MainState(
    val user: User? = null,
    val loading: Boolean = false
)