package com.be.models

import com.be.dto.Jot
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import java.time.LocalDateTime


@Document("UserOne")
class UserModel{
    @Id
    var id = ObjectId.get().toString()
    @Field(name = "username")
    @Indexed(unique = true)
    var username = ""
    @Field(name= "passcode")
    var passcode = ""
        get() = field
        set(value) {
            val passcodeEncoder = BCryptPasswordEncoder()
            field = passcodeEncoder.encode(value)
        }
    @Field(name = "createDate")
    var createdDate = LocalDateTime.now()
//    @Field( name = "updated")
//    var updatedDate = LocalDateTime.now()
    @Field(name = "jot")
    var note = mutableListOf<com.be.models.Jot>()



    // comparing the passcode that is sent to us from the login endpoint/function and the passcode we have in our database
    fun comparePasscode(passcode: String):Boolean{
        return BCryptPasswordEncoder().matches(passcode,this.passcode)
    }
}