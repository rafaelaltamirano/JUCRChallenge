package com.example.jucrchallenge.ui.main

import com.example.jucrchallenge.domain.entities.User

data class MainState(
    val user: User? = null,
    val loading: Boolean = false,
    val showSplash : Boolean = true
)