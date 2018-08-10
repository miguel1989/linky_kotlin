package linky.user

import org.junit.Assert
import linky.BasicIntegrationTest
import org.junit.Test
import org.springframework.http.*

class CreateUserIT : BasicIntegrationTest() {
    @Test
    fun createUser_correct() {
        userAdminApi.deleteUserByEmail("aaa@aaa.lv")
        val authenticatedUserBean = userApi.registerAndAssert("aaa@aaa.lv", "secret", "medved")

        val fullUserResponse = userAdminApi.findUserById(authenticatedUserBean.id)
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
        Assert.assertEquals(meResponse.body, authenticatedUserBean.id)

        val meFullResponse = userApi.meFull("aaa@aaa.lv", "secret")
        Assert.assertNotNull(meFullResponse)
        Assert.assertEquals(meFullResponse.statusCode, HttpStatus.OK)
        Assert.assertEquals(meFullResponse.body.id, authenticatedUserBean.id)
        Assert.assertEquals(meFullResponse.body.email, "aaa@aaa.lv")
        Assert.assertEquals(meFullResponse.body.name, "medved")
    }

//    @Test
//    fun createUser_duplicate() {
//        userAdminApi.deleteUserByEmail("aaa@aaa.lv")
//        val authenticatedUserBean = userApi.registerAndAssert("aaa@aaa.lv", "secret", "medved")
//
//        userApi.register("aaa@aaa.lv")
//    }
}