package linky

import linky.infra.command.PipedNow
import linky.link.create.CreateLinkCommand
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(TEST)
class TestInitDataBean(private val pipedNow: PipedNow) : InitializingBean {
    override fun afterPropertiesSet() {
        pipedNow.execute(CreateLinkCommand("test", "test", "www.test.lv"))
    }
}