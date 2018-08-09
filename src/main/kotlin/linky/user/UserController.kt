package linky.user

import linky.infra.command.PipedNow
import linky.user.create.CreateUserCommand
import linky.user.dto.AuthenticatedUserBean
import linky.user.dto.CreateUserBean
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val pipedNow: PipedNow) {
    @PostMapping("/create")
    fun create(@RequestBody bean: CreateUserBean): AuthenticatedUserBean {
        return pipedNow.execute(CreateUserCommand(bean.email, bean.password, bean.name))
    }
}