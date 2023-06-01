package com.example.jucrchallenge.data.dao

import com.example.jucrchallenge.domain.entities.Car
import com.example.jucrchallenge.domain.entities.SuperCharges
import com.example.jucrchallenge.domain.entities.User

interface UserDao {
    suspend fun getUser(): User
    suspend fun getCar(): Car
    suspend fun getSuperCharges(): List<SuperCharges>
}