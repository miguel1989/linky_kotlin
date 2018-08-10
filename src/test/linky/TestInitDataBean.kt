package linky

import linky.infra.command.PipedNow
import linky.link.create.CreateLinkCommand
import linky.user.create.CreateAdminCommand
import linky.user.create.CreateUserCommand
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(TEST)
class TestInitDataBean(private val pipedNow: PipedNow) : InitializingBean {
    override fun afterPropertiesSet() {
        CreateUserCommand(TEST_USER_EMAIL, TEST_USER_PASS, "Test user").execute(pipedNow)
        CreateAdminCommand(ADMIN_USER_EMAIL, TEST_USER_PASS, "Admin user").execute(pipedNow)
//        pipedNow.execute(CreateLinkCommand("test", "test", "www.test.lv"))
    }
}