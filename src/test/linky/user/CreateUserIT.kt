package linky.user

import org.junit.Assert
import linky.BasicIntegrationTest
import linky.user.dto.AuthenticatedUserBean
import linky.user.dto.CreateUserBean
import org.junit.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class CreateUserIT: BasicIntegrationTest() {
    @Test
    fun createUser() {
        val request: HttpEntity<CreateUserBean> = HttpEntity(CreateUserBean("aaa@aaa.lv", "secret", "medved"))
        val result = testRestTemplate.exchange(
                "http://localhost:$randomServerPort/api/user/create",
                HttpMethod.POST,
                request,
                AuthenticatedUserBean::class.java
        )
        Assert.assertNotNull(result)
        Assert.assertEquals(result.statusCode, HttpStatus.OK)
        Assert.assertNotNull(result.body.id)
        Assert.assertEquals(result.body.email, "aaa@aaa.lv")
        Assert.assertEquals(result.body.name, "medved")
    }
}