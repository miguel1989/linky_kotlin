package linky.user

import linky.infra.command.PipedNow
import linky.user.delete.DeleteUserCommand
import linky.user.dto.FullUserBean
import linky.user.find.FindFullUserCommand
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/user")
class UserAdminController(private val pipedNow: PipedNow) {
    @GetMapping("/{id:.*}")
    fun find(@PathVariable("id") id: String): FullUserBean {
        return FindFullUserCommand(id).execute(pipedNow)
    }

    @DeleteMapping("/{email:.*}")
    fun delete(@PathVariable("email") email: String): String {
        DeleteUserCommand(email).execute(pipedNow)
        return "ok" //todo maybe baseResponse?
    }
}