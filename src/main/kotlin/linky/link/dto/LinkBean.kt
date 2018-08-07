package linky.link.dto

import linky.infra.command.Return
import linky.link.domain.Link

data class LinkBean(val id: String, val name: String, val url: String) : Return {
    constructor(link: Link) : this(link.id(), link.name(), link.url())
}