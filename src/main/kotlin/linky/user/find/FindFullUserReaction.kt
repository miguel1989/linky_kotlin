package linky.user.find

import linky.infra.reaction.Reaction
import linky.user.UserDao
import linky.user.dto.FullUserBean
import org.springframework.stereotype.Component
import java.util.*

@Component
class FindFullUserReaction(
        private val userDao: UserDao
) : Reaction<FindFullUserCommand, FullUserBean> {
    override fun react(command: FindFullUserCommand): FullUserBean {
        return FullUserBean(userDao.findOne(UUID.fromString(command.id)))
    }
}