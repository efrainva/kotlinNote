package com.be.service

import com.be.dto.Note
import com.be.models.UserModel
import com.be.repositories.UserRepo

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepo: UserRepo) {
    fun save (user: UserModel): UserModel{
        return this.userRepo.save(user)
    }
    fun findbyUsername(username: String): UserModel?{
        return this.userRepo.findByUsername(username)
    }
    fun deleteUser(id:String){
         return this.userRepo.deleteById(id)
    }

    fun getById(id: String):UserModel{
        return this.userRepo.getById(id)
    }
    fun updateNote(id:String,user: Note):UserModel{
        val userUpdate = getById(id)
        val updatedUser = userRepo.save(
            userUpdate.apply{
                title = user.Title
                note = user.Note
            }

        )
        return updatedUser
    }
}