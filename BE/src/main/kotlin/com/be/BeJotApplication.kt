package com.be


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class BeJotApplication

fun main(args: Array<String>) {
	runApplication<BeJotApplication>(*args)
}

@RestController
class Man{
//	learning simple object array concepts in koltin
	@GetMapping
	fun index():ResponseEntity<Any> {
	return ResponseEntity.ok("hello")
    }
}

//data class Message(val id: String,val text:String, var ms: MutableList<temp>)
//data class temp (var no:String,var e:String)