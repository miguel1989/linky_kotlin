package linky.user.find

import linky.infra.command.Command
import linky.user.dto.AuthenticatedUserBean

class FindUserCommand(
        val id: String
) : Command<AuthenticatedUserBean> {
    override fun toLogString(): String {
        return "FindUserCommand id = $id"
    }
}