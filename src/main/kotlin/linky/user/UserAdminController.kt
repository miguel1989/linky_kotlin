package linky.user

import linky.infra.command.PipedNow
import linky.user.dto.FullUserBean
import linky.user.find.FindFullUserCommand
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/user")
class UserAdminController(private val pipedNow: PipedNow) {
    @GetMapping("/{id:.*}")
    fun find(@PathVariable("id") id: String): FullUserBean {
        return FindFullUserCommand(id).execute(pipedNow)
    }
}