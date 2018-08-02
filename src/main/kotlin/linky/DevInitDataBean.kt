package linky

import linky.infra.command.PipedNow
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
class DevInitDataBean(private val pipedNow: PipedNow) : InitializingBean {
    override fun afterPropertiesSet() {
        pipedNow.execute(CreateLink("user1", "gogle", "www.google.lv"))
    }
}