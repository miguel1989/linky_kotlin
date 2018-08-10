package linky.user.find

import linky.infra.command.Command
import linky.infra.command.TxFlag
import linky.infra.command.TxFlagReadOnly
import linky.user.dto.AuthenticatedUserBean

class FindUserCommand(
        val id: String
) : Command<AuthenticatedUserBean> {
    override fun toLogString(): String {
        return "FindUserCommand id = $id"
    }

    override fun txFlags(): Collection<TxFlag> {
        return listOf(TxFlagReadOnly())
    }
}