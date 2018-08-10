package linky.user.delete

import linky.infra.command.Command
import linky.infra.command.Return

class DeleteUserCommand(val email: String) : Command<Return.Void> {
    override fun toLogString(): String {
        return "DeleteUserCommand email = $email"
    }
}