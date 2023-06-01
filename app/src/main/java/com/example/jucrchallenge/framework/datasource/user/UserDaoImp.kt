package com.example.jucrchallenge.framework.datasource.user

import com.example.jucrchallenge.data.dao.UserDao
import com.example.jucrchallenge.domain.entities.User
import com.google.gson.Gson
import javax.inject.Inject


class UserDaoImp @Inject constructor(
): UserDao {

    override suspend fun getUser(): User {
        val json = "{\"id\":\"123456\",\"email\":\"example@example.com\",\"password\":\"mypassword\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"address\":\"123 Street\"}"
        val gson = Gson()
        return gson.fromJson(json, User::class.java)
    }
}