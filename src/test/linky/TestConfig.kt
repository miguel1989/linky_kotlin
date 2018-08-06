package linky

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class TestConfig {

    @Bean
    @Profile(TEST)
    fun testRestTemplate(): TestRestTemplate {
        return TestRestTemplate()
    }
}