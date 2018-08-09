package linky.user.create

import linky.infra.command.Command
import linky.user.dto.AuthenticatedUserBean

class CreateUserCommand(
        val email: String,
        val password: String,
        val name: String
) : Command<AuthenticatedUserBean> {
    override fun toLogString(): String {
        return "CreateUserCommand email = $email, pass = $password, name = $name"
    }
}