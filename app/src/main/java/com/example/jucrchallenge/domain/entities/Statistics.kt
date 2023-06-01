package com.example.jucrchallenge.domain.entities

import com.example.jucrchallenge.domain.enum.Escale

data class Statistics (
    val scale: Escale,
    val quantity: String,
)