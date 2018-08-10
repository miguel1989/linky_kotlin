package linky.api

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class UserApi(
        private val testRestTemplate: TestRestTemplate
) : BaseApi() {
    fun me(email: String, pass: String): ResponseEntity<String> {
        val httpHeaders = HttpHeaders()
        httpHeaders.set(HttpHeaders.AUTHORIZATION,
                buildBasicAuth(email, pass))
        val request = HttpEntity<String>(httpHeaders)
        return testRestTemplate.exchange(
                "$localUrl/api/user/me",
                HttpMethod.GET,
                request,
                String::class.java
        )
    }
}