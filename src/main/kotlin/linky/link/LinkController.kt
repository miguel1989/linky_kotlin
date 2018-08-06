package linky.link

import linky.infra.command.PipedNow
import linky.link.create.CreateLink
import linky.link.dto.LinkBean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/link")
class LinkController(private val pipedNow: PipedNow) {
    @GetMapping("/{id:.*}")
    fun findLink(): LinkBean {
        return pipedNow.execute(CreateLink("user1", "gogle", "www.google.lv"))
    }
}