package com.example.jucrchallenge.framework.di

import com.example.jucrchallenge.data.dao.UserDao
import com.example.jucrchallenge.framework.datasource.user.UserDaoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindUserDao(imp: UserDaoImp): UserDao

}