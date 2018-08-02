package linky

import linky.infra.reaction.Reaction
import org.springframework.stereotype.Component

@Component
class CreateLinkReaction : Reaction<CreateLink, LinkBean> {
    override fun react(command: CreateLink): LinkBean {
        return LinkBean("something")
    }
}