package linky.user.find

import linky.infra.command.Command
import linky.user.dto.FullUserBean

class FindFullUserCommand(
        val id: String
): Command<FullUserBean> {
    override fun toLogString(): String {
        return "FindFullUserCommand id = $id"
    }
}