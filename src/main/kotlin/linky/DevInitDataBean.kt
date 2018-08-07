package linky

import linky.infra.command.PipedNow
import linky.link.create.CreateLinkCommand
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(DEV)
class DevInitDataBean(private val pipedNow: PipedNow) : InitializingBean {
    override fun afterPropertiesSet() {
        pipedNow.execute(CreateLinkCommand("user1", "gogle", "www.google.lv"))
    }
}