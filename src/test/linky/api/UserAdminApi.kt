package linky.api

import linky.ADMIN_USER_EMAIL
import linky.TEST_USER_PASS
import linky.user.dto.FullUserBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class UserAdminApi(
        private val testRestTemplate: TestRestTemplate
) : BaseApi() {
    fun findUserById(id: String): ResponseEntity<FullUserBean> {
        val httpHeaders = HttpHeaders()
        httpHeaders.set(HttpHeaders.AUTHORIZATION,
                buildBasicAuth(ADMIN_USER_EMAIL, TEST_USER_PASS))

        val request = HttpEntity<FullUserBean>(httpHeaders)
        return testRestTemplate.exchange(
                "$localUrl/admin/user/$id",
                HttpMethod.GET,
                request,
                FullUserBean::class.java
        )
    }
}