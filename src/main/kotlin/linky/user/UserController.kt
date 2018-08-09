package linky.user

import linky.infra.command.PipedNow
import linky.user.dto.AuthenticatedUserBean
import linky.user.find.FindUserCommand
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val pipedNow: PipedNow) {
    @GetMapping("/{id:.*}")
    fun find(@PathVariable("id") id: String): AuthenticatedUserBean {
        return FindUserCommand(id).execute(pipedNow)
    }

//    @PostMapping("/create")
//    fun create(@RequestBody bean: CreateUserBean): AuthenticatedUserBean {
//        return pipedNow.execute(CreateUserCommand(bean.email, bean.password, bean.name))
//    }
}