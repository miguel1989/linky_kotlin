package linky.link.create

import linky.link.dto.LinkBean
import linky.infra.reaction.Reaction
import linky.link.dao.LinkDao
import linky.link.domain.Link
import org.springframework.stereotype.Component

@Component
class CreateLinkReaction(private val linkDao: LinkDao) : Reaction<CreateLinkCommand, LinkBean> {
    override fun react(command: CreateLinkCommand): LinkBean {
        val link = Link(command.name, command.url, command.userId)
        linkDao.save(link)
        return LinkBean(link)
    }
}