package linky.user

import linky.user.create.CreateAdminCommand
import linky.user.create.CreateAdminReaction
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@RunWith(MockitoJUnitRunner::class)
class CreateAdminReactionTest {

    @Test
    fun correct_react() {
        val userDao = Mockito.mock(UserDao::class.java)
        val passwordEncoder = BCryptPasswordEncoder()
        val reaction = CreateAdminReaction(userDao, passwordEncoder)

        val authUserBean = reaction.react(CreateAdminCommand("admin@linky.lv", "admin!", "Admin User"))
        Assert.assertNotNull(authUserBean.id)
        Assert.assertEquals(authUserBean.email, "admin@linky.lv")
        Assert.assertEquals(authUserBean.name, "Admin User")
        Assert.assertEquals(authUserBean.roles.size, 2)
        Assert.assertEquals(authUserBean.roles[0].value, "ROLE_USER")
        Assert.assertEquals(authUserBean.roles[1].value, "ROLE_ADMIN")
    }
}