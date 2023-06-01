package com.example.jucrchallenge.framework.datasource.user

import com.example.jucrchallenge.data.dao.UserDao
import com.example.jucrchallenge.domain.entities.Car
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

    override suspend fun getCar(): Car {
        val json = "{\"carModel\":\"Tesla model X\",\"statistics\":[{\"scale\":\"VOLTAGE\",\"quantity\":\"240\"},{\"scale\":\"REMAINING_CHARGE\",\"quantity\":\"540\"},{\"scale\":\"FAST_CHARGE\",\"quantity\":\"20\"}]}"
        val gson = Gson()
        return gson.fromJson(json, Car::class.java)
    }
}