package com.be.exceptions

import org.springframework.http.HttpStatus

class UserNotFoundExceptions (val statusCode: HttpStatus,var reason:String):Exception()
