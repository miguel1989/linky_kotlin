package linky.user

import org.junit.Assert
import linky.BasicIntegrationTest
import linky.user.dto.AuthenticatedUserBean
import linky.user.dto.CreateUserBean
import org.junit.Test
import org.springframework.http.*

class CreateUserIT : BasicIntegrationTest() {
    @Test
    fun createUser_correct() {
        val request: HttpEntity<CreateUserBean> = HttpEntity(CreateUserBean("aaa@aaa.lv", "secret", "medved"))
        val registerResult = testRestTemplate.exchange(
                localUrl() + "/service/register",
//                "http://localhost:$randomServerPort/service/register",
                HttpMethod.POST,
                request,
                AuthenticatedUserBean::class.java
        )
        Assert.assertNotNull(registerResult)
        Assert.assertEquals(registerResult.statusCode, HttpStatus.OK)
        Assert.assertNotNull(registerResult.body.id)
        Assert.assertEquals(registerResult.body.email, "aaa@aaa.lv")
        Assert.assertEquals(registerResult.body.name, "medved")

//        val userResponse = findUserById(result.body.id)
        val fullUserResponse = userAdminApi.findUserById(registerResult.body.id)
        Assert.assertNotNull(fullUserResponse)
        Assert.assertEquals(fullUserResponse.statusCode, HttpStatus.OK)
        Assert.assertEquals(fullUserResponse.body.email, "aaa@aaa.lv")
        Assert.assertEquals(fullUserResponse.body.name, "medved")
        Assert.assertNotEquals(fullUserResponse.body.password, "secret") //todo
        Assert.assertEquals(fullUserResponse.body.roles.size, 1)
        Assert.assertEquals(fullUserResponse.body.roles[0].value, "ROLE_USER")

        val meResponse = userApi.me("aaa@aaa.lv", "secret")
        Assert.assertNotNull(meResponse)
        Assert.assertEquals(meResponse.statusCode, HttpStatus.OK)
        Assert.assertEquals(meResponse.body, registerResult.body.id)
    }
}