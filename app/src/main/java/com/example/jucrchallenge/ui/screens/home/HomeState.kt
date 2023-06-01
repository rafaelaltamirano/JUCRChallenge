package com.example.jucrchallenge.ui.screens.home

import com.example.jucrchallenge.domain.entities.Car
import com.example.jucrchallenge.domain.entities.User


data class HomeState(
    val car: Car? = null,
    val loading: Boolean = false,
)