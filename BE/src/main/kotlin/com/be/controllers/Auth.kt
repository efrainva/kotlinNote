package com.be.controllers

import com.be.dto.Message
import com.be.dto.Login
import com.be.dto.Jot
import com.be.models.UserModel
import com.be.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/auth")
class Auth(private val userService: UserService){
//    post end point for registring user
    @PostMapping("register")
    fun register(@RequestBody body: Login): ResponseEntity<UserModel>{
        val user = UserModel()
            user.username = body.username
            user.passcode = body.passcode

        return ResponseEntity.ok(this.userService.save(user))
    }
//post end point for login: also sends cookie to the front end
    @PostMapping("login")
    fun login(@RequestBody body: Login, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findbyUsername(body.username)
            ?: return ResponseEntity.badRequest().body(Message("user not found!"))

        if (!user.comparePasscode(body.passcode)) {
            return ResponseEntity.badRequest().body(Message("invalid password!"))
        }

        val issuer = user.id

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)
        return ResponseEntity.ok(Message("success"))
    }
    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

            return ResponseEntity.ok(this.userService.getById(body.issuer))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
    }



    @DeleteMapping ("user/{id}")
    fun deleteUser(@CookieValue("jwt") jwt: String?,@PathVariable("id") userid:String,response: HttpServletResponse): ResponseEntity<Any>{
        try {
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }else if(body.issuer == userid){
                this.userService.deleteUser(userid)
                return ResponseEntity.ok("deleted")
            }else{
                ResponseEntity.status(401).body(Message("wrong user"))
            }
               return  ResponseEntity.ok("calling")

        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
    }

    @PutMapping("user/{id}")
    fun addnote(@CookieValue("jwt") jwt: String?,@PathVariable("id") userid:String,@RequestBody request:Jot): ResponseEntity<Any>{
        try {
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }else if(body.issuer == userid){

                this.userService.updateNote(userid,request.title,request.note)
                return ResponseEntity.ok("updating")

            }else{
                ResponseEntity.status(401).body(Message("wrong user"))
            }
            return  ResponseEntity.ok("calling")

        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
    }



}