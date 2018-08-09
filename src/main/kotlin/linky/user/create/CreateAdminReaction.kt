package linky.user.create

import linky.infra.reaction.Reaction
import linky.user.UserDao
import linky.user.domain.User
import linky.user.dto.AuthenticatedUserBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CreateAdminReaction(
        private val userDao: UserDao,
        private val passwordEncoder: PasswordEncoder
) : Reaction<CreateAdminCommand, AuthenticatedUserBean> {
    override fun react(command: CreateAdminCommand): AuthenticatedUserBean {
        val user = User(command.email, passwordEncoder.encode(command.password), command.name)
        user.grantAdmin()
        userDao.save(user)
        return AuthenticatedUserBean(user)
    }
}