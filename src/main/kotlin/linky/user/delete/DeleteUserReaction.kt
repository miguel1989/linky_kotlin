package linky.user.delete

import linky.infra.command.Return
import linky.infra.reaction.Reaction
import linky.user.UserDao
import org.springframework.stereotype.Component

@Component
class DeleteUserReaction(
        private val userDao: UserDao
) : Reaction<DeleteUserCommand, Return.Void> {
    override fun react(command: DeleteUserCommand): Return.Void {
        val user = userDao.findByEmail(command.email)
        if (user != null) {
            userDao.delete(user)
        }
        return Return.Void()
    }
}