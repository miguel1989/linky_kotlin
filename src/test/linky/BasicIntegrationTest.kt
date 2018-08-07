package linky

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import linky.link.dto.CreateLinkBean
import linky.link.dto.LinkBean
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class, TestConfig::class])
@ActiveProfiles(TEST)
class BasicIntegrationTest {
    private val logger = LoggerFactory.getLogger(BasicIntegrationTest::class.java)

    @LocalServerPort
    internal var randomServerPort: Int = 0

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    val TEST_USER_EMAIL: String = "user@test.lv"
    val TEST_USER_PASS: String = "secret"

    @Before
    fun setup() {
        logger.info("setup on port $randomServerPort")
    }

    @Test
    fun createLink() {
        val request: HttpEntity<CreateLinkBean> = HttpEntity(CreateLinkBean("gogle", "www.google.lv"))
        val result = testRestTemplate.exchange(
                "http://localhost:$randomServerPort/api/link/create",
                HttpMethod.POST,
                request,
                LinkBean::class.java
        )
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertNotNull(result.body.id)
        assertEquals(result.body.name, "gogle")
        assertEquals(result.body.url, "www.google.lv")
//        val result = testRestTemplate.getForEntity("http://localhost:$randomServerPort/api/link/1", LinkBean::class.java)
//        assertNotNull(result)
//        assertEquals(result.statusCode, HttpStatus.OK)
//        assertEquals(result.body, LinkBean("gogle", "www.google.lv"))
//        testRestTemplate.exchange("/api/link", HttpMethod.GET, LinkBean::class)
    }
}
