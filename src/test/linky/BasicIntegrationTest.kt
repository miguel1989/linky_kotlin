package linky

import linky.api.UserAdminApi
import linky.api.UserApi
import org.junit.Before
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class, TestConfig::class])
@ActiveProfiles(TEST)
class BasicIntegrationTest {
    protected val logger = LoggerFactory.getLogger(BasicIntegrationTest::class.java)!!

    @LocalServerPort
    internal var randomServerPort: Int = 0

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var userAdminApi: UserAdminApi

    @Autowired
    lateinit var userApi: UserApi

    @Before
    fun setup() {
        logger.info("setup on port $randomServerPort")
        userAdminApi.localUrl = localUrl()
        userApi.localUrl = localUrl()
    }

    fun localUrl(): String {
        return "http://localhost:$randomServerPort"
    }
}
