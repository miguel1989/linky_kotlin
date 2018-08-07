package linky.link.create

import linky.link.dto.LinkBean
import linky.infra.command.Command

class CreateLinkCommand(val userId: String, val name: String, val url: String) : Command<LinkBean> {
    override fun toLogString(): String {
        return "CreateLinkCommand userId = $userId, name = $name, url = $url"
    }
}