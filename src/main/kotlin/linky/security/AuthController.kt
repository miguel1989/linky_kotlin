package linky.security

import linky.infra.command.PipedNow
import linky.user.create.CreateUserCommand
import linky.user.dto.AuthenticatedUserBean
import linky.user.dto.CreateUserBean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val pipedNow: PipedNow) {
    @PostMapping("/service/register")
    fun create(@RequestBody bean: CreateUserBean): AuthenticatedUserBean {
        val authBean = CreateUserCommand(bean.email, bean.password, bean.name).execute(pipedNow)

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(authBean.id, null, authBean.roles)
        return authBean
    }
}