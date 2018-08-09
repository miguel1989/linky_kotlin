package linky.user.create

import linky.infra.reaction.Reaction
import linky.user.UserDao
import linky.user.domain.User
import linky.user.dto.AuthenticatedUserBean
import org.springframework.stereotype.Component

@Component
class CreateUserReaction(private val userDao: UserDao) : Reaction<CreateUserCommand, AuthenticatedUserBean> {
    override fun react(command: CreateUserCommand): AuthenticatedUserBean {
        //todo PasswordEncoder
        val user = User(command.email, command.password, command.name)
        userDao.save(user)
        return AuthenticatedUserBean(user)
    }
}