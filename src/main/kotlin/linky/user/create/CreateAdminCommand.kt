package linky.user.create

import linky.infra.command.Command
import linky.user.dto.AuthenticatedUserBean

class CreateAdminCommand(
        val email: String,
        val password: String,
        val name: String
) : Command<AuthenticatedUserBean> {
    override fun toLogString(): String {
        return "CreateAdminCommand email = $email, name = $name"
    }
}