package com.be.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

//@Document
class Jot {
    @Id
    var id = ObjectId.get().toString()
    @Field(name = "title")
    var title = ""
    @Field(name = "note")
    var note = ""

}