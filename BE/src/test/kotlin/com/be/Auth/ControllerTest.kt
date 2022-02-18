package com.be.Auth


import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import java.net.URI

@ExtendWith(SpringExtension::class)
@SpringBootTest
class ControllerTest {
    private fun applicationURI() = "http://localhost:8080"
    var test = TestRestTemplate()

    @Test
    fun testRegister(){

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