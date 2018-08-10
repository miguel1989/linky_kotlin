package linky.user.find

import linky.infra.command.Command
import linky.infra.command.TxFlag
import linky.infra.command.TxFlagReadOnly
import linky.user.dto.FullUserBean

class FindFullUserCommand(
        val id: String
) : Command<FullUserBean> {
    override fun toLogString(): String {
        return "FindFullUserCommand id = $id"
    }

    override fun txFlags(): Collection<TxFlag> {
        return listOf(TxFlagReadOnly())
    }
}