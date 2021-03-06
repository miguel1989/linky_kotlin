package linky.user.create

import linky.infra.reaction.Reaction
import linky.user.UserDao
import linky.user.domain.User
import linky.user.dto.AuthenticatedUserBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CreateUserReaction(
        private val userDao: UserDao,
        private val passwordEncoder: PasswordEncoder
) : Reaction<CreateUserCommand, AuthenticatedUserBean> {
    override fun react(command: CreateUserCommand): AuthenticatedUserBean {
        val user = User(command.email, passwordEncoder.encode(command.password), command.name)
        userDao.save(user)
        return AuthenticatedUserBean(user)
    }
}