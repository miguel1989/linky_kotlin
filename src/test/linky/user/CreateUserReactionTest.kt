package linky.user

import linky.user.create.CreateUserCommand
import linky.user.create.CreateUserReaction
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@RunWith(MockitoJUnitRunner::class)
class CreateUserReactionTest {

    @Test
    fun correct_react() {
        val userDao = mock(UserDao::class.java)
        val passwordEncoder = BCryptPasswordEncoder()
        val reaction = CreateUserReaction(userDao, passwordEncoder)

//        verify(userDao.save(Matchers.anyObject()))
        val authUserBean = reaction.react(CreateUserCommand("mmm@mmm.lv", "medved", "krevedko"))
        Assert.assertNotNull(authUserBean.id)
        Assert.assertEquals(authUserBean.email, "mmm@mmm.lv")
        Assert.assertEquals(authUserBean.name, "krevedko")
        Assert.assertEquals(authUserBean.roles.size, 1)
        Assert.assertEquals(authUserBean.roles[0].value, "ROLE_USER")
//        when(userDao.save(Matchers.anyObject())).thenReturn()

    }
}