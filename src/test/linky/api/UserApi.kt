package linky.api

import linky.user.dto.AuthenticatedUserBean
import linky.user.dto.CreateUserBean
import org.junit.Assert
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.stereotype.Component

@Component
class UserApi(
        private val testRestTemplate: TestRestTemplate
) : BaseApi() {
    fun me(email: String, pass: String): ResponseEntity<String> {
        val httpHeaders = HttpHeaders()
        httpHeaders.set(HttpHeaders.AUTHORIZATION, buildBasicAuth(email, pass))
        val request = HttpEntity<String>(httpHeaders)
        return testRestTemplate.exchange(
                "$localUrl/api/user/me",
                HttpMethod.GET,
                request,
                String::class.java
        )
    }

    fun meFull(email: String, pass: String): ResponseEntity<AuthenticatedUserBean> {
        val httpHeaders = HttpHeaders()
        httpHeaders.set(HttpHeaders.AUTHORIZATION, buildBasicAuth(email, pass))
        val request = HttpEntity<String>(httpHeaders)
        return testRestTemplate.exchange(
                "$localUrl/api/user/me/full",
                HttpMethod.GET,
                request,
                AuthenticatedUserBean::class.java
        )
    }

    fun register(email: String, pass: String = "secret", name: String = "Test user"): ResponseEntity<AuthenticatedUserBean> {
        val request: HttpEntity<CreateUserBean> = HttpEntity(CreateUserBean(email, pass, name))
        return testRestTemplate.exchange(
                "$localUrl/service/register",
                HttpMethod.POST,
                request,
                AuthenticatedUserBean::class.java
        )
    }

    fun registerAndAssert(email: String, pass: String = "secret", name: String = "Test user"): AuthenticatedUserBean {
        val response = register(email, pass, name)
        Assert.assertNotNull(response)
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertNotNull(response.body.id)
        Assert.assertEquals(response.body.email, email)
        Assert.assertEquals(response.body.name, name)
        Assert.assertEquals(response.body.roles.size, 1)
        Assert.assertEquals(response.body.roles[0].value, "ROLE_USER")
        return response.body
    }
}