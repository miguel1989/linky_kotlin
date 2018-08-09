package linky.user.find

import linky.infra.reaction.Reaction
import linky.user.UserDao
import linky.user.dto.AuthenticatedUserBean
import org.springframework.stereotype.Component
import java.util.*

@Component
class FindUserReaction(
        private val userDao: UserDao
) : Reaction<FindUserCommand, AuthenticatedUserBean> {
    override fun react(command: FindUserCommand): AuthenticatedUserBean {
        return AuthenticatedUserBean(userDao.findOne(UUID.fromString(command.id)))
    }
}