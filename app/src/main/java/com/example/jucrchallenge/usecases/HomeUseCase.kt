package com.example.jucrchallenge.usecases

import com.example.jucrchallenge.data.dao.UserDao
import com.example.jucrchallenge.domain.entities.Car
import com.example.jucrchallenge.domain.entities.SuperCharges
import com.example.jucrchallenge.domain.entities.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val userDao: UserDao) {

    suspend fun getCar(): Car {
        delay(3000)
        return userDao.getCar()
    }

    suspend fun getSuperCharges(): List<SuperCharges> {
        delay(3000)
        return userDao.getSuperCharges()
    }
}

