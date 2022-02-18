package com.be.service

import com.be.models.Jot
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
    fun addNote(id:String,a:String,b:String):UserModel{

        val userUpdate = getById(id)
        var newnote = Jot()
        newnote.title = a
        newnote.note = b
        val updatedUser = userRepo.save(
            userUpdate.apply{
                this.note.add(newnote)
            }
        )
        return updatedUser

    }


}

private fun <E> MutableList<E>.add(element: Jot) {

}

