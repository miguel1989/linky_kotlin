package linky.link

import linky.infra.command.PipedNow
import linky.link.create.CreateLinkCommand
import linky.link.dto.CreateLinkBean
import linky.link.dto.LinkBean
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/link")
class LinkController(private val pipedNow: PipedNow) {
    @GetMapping("/{id:.*}")
    fun findLink(): LinkBean {
        return pipedNow.execute(CreateLinkCommand("user1", "gogle", "www.google.lv"))
    }

    @PostMapping("/create")
    fun createLink(@RequestBody createLinkBean: CreateLinkBean): LinkBean {
        return pipedNow.execute(CreateLinkCommand("miguel", createLinkBean.name, createLinkBean.url))
    }
}