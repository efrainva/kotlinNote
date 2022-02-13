package com.be


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
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
//	fun index() {
//	var ob = Message("1","message one ", mutableListOf( temp("wow","ok"),temp("a","a")))
//		ob.ms.add(temp("wow","ok"))
//	print(ob)
//
//}


	@GetMapping
	fun index(): List<Message> = mutableListOf(
		Message("1","message one ", listOf( temp("wow","ok"),temp("a","a"))),
//		Message("2", "message two")

	)
//	 strictly used to store data
	 data class obs (var obs : String )

}


data class Message(val id: String,val text:String, var ms: List<temp>)
data class temp (var no:String,var e:String)