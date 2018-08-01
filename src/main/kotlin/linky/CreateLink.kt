package linky

import linky.infra.Command

class CreateLink(val userId:String, val name:String, val url:String) : Command<LinkBean> {
    override fun toLogString(): String {
        return "CreateLink userId = $userId, name = $name, url = $url"
    }
}