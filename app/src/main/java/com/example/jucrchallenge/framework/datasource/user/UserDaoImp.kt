package com.example.jucrchallenge.data.datasource.user

import javax.inject.Inject

class UserLDaoImp {
}

class UserLDaoImp @Inject constructor(
): SessionDao {

    override suspend fun closeOtherSessions(): String {
        val res = api.closeOthersSession()
        ApiTools.validateResponseOrFail(res)
        return res.body()!!.data.resultData.toEntity()
    }

    override suspend fun logOutCurrentSession(): String {
        val res = api.closeCurrentSession()
        ApiTools.validateResponseOrFail(res)
        return res.body()!!.data.resultData.toEntity()
    }

    override suspend fun logOutLastSession(): String {
        val res = api.closeLastSession()
        ApiTools.validateResponseOrFail(res)
        return res.body()!!.data.resultData.toEntity()
    }
}