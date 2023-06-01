package com.example.jucrchallenge.usecases

import com.example.jucrchallenge.data.dao.UserDao
import com.example.jucrchallenge.domain.entities.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val userDao: UserDao) {

//    suspend fun getUser(): User {
//        delay(3000)
//        return userDao.getUser()
//    }
}

