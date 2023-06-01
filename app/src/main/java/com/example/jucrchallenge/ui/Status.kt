package com.example.jucrchallenge.ui

enum class Status {
    ERROR,
}

data class ModelStatus(
    val status: Status,
    val message: String? = null
)

data class ErrorStatus(
    val showError: Boolean = false,
)
