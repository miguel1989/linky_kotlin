package linky.user

import linky.ADMIN_USER_EMAIL
import org.junit.Assert
import linky.BasicIntegrationTest
import linky.TEST_USER_PASS
import linky.user.dto.AuthenticatedUserBean
import linky.user.dto.CreateUserBean
import org.apache.tomcat.util.codec.binary.Base64
import org.junit.Test
import org.springframework.http.*
import java.nio.charset.StandardCharsets

class CreateUserIT: BasicIntegrationTest() {
    @Test
    fun createUser_correct() {
        val request: HttpEntity<CreateUserBean> = HttpEntity(CreateUserBean("aaa@aaa.lv", "secret", "medved"))
        val result = testRestTemplate.exchange(
                localUrl() + "/service/register",
//                "http://localhost:$randomServerPort/service/register",
                HttpMethod.POST,
                request,
                AuthenticatedUserBean::class.java
        )
        Assert.assertNotNull(result)
        Assert.assertEquals(result.statusCode, HttpStatus.OK)
        Assert.assertNotNull(result.body.id)
        Assert.assertEquals(result.body.email, "aaa@aaa.lv")
        Assert.assertEquals(result.body.name, "medved")

        val userResponse = findUserById(result.body.id)
        Assert.assertNotNull(userResponse)
        Assert.assertEquals(userResponse.statusCode, HttpStatus.OK)
        Assert.assertEquals(result.body.email, "aaa@aaa.lv")
        Assert.assertEquals(result.body.name, "medved")
    }

    fun findUserById(id: String): ResponseEntity<AuthenticatedUserBean> {
        val httpHeaders = HttpHeaders()
        httpHeaders.set(HttpHeaders.AUTHORIZATION,
                buildBasicAuth(ADMIN_USER_EMAIL, TEST_USER_PASS))

        val request :HttpEntity<AuthenticatedUserBean> = HttpEntity<AuthenticatedUserBean>(null, httpHeaders)
        return testRestTemplate.exchange(
                localUrl() + "/api/user/$id",
                HttpMethod.GET,
                request,
                AuthenticatedUserBean::class.java
        )
    }

    fun buildBasicAuth(email:String, pass: String): String {
        val auth = "$email:$pass"
        val encodedAuth = Base64.encodeBase64(auth.toByteArray(StandardCharsets.UTF_8))
        val str = String(encodedAuth)
        return "Basic $str"
    }
}