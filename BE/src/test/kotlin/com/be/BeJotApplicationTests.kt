package com.be

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import java.net.URI

@SpringBootTest
class BeJotApplicationTests {
	private fun applicationURI() = "http://localhost:8080"

	@Test
	fun contextLoads() {
	}
	var test = TestRestTemplate()

	fun testIndex(){

		var result = test.exchange(
			URI(applicationURI()+ "/"),
			HttpMethod.GET,
			HttpEntity(""),
			String::class.java
		)
		Assertions.assertEquals(HttpStatus.OK,result.statusCode)
		Assertions.assertEquals("hello",result.body)
	}

}
