package linky.link

import org.junit.Assert
import linky.BasicIntegrationTest
import linky.link.dto.CreateLinkBean
import linky.link.dto.LinkBean
import org.junit.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class LinkIT: BasicIntegrationTest() {
    @Test
    fun createLink() {
        val request: HttpEntity<CreateLinkBean> = HttpEntity(CreateLinkBean("gogle", "www.google.lv"))
        val result = testRestTemplate.exchange(
                "http://localhost:$randomServerPort/api/link/create",
                HttpMethod.POST,
                request,
                LinkBean::class.java
        )
        Assert.assertNotNull(result)
        Assert.assertEquals(result.statusCode, HttpStatus.OK)
        Assert.assertNotNull(result.body.id)
        Assert.assertEquals(result.body.name, "gogle")
        Assert.assertEquals(result.body.url, "www.google.lv")
//        val result = testRestTemplate.getForEntity("http://localhost:$randomServerPort/api/link/1", LinkBean::class.java)
//        assertNotNull(result)
//        assertEquals(result.statusCode, HttpStatus.OK)
//        assertEquals(result.body, LinkBean("gogle", "www.google.lv"))
//        testRestTemplate.exchange("/api/link", HttpMethod.GET, LinkBean::class)
    }
}