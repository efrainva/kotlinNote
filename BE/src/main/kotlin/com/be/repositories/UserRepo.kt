package com.be.repositories

import com.be.exceptions.UserNotFoundExceptions
import com.be.models.UserModel
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepo :MongoRepository<UserModel,String> {
    fun findByUsername(username:String): UserModel
    fun getById(id:String):UserModel
}