package linky.user

import linky.infra.command.PipedNow
import linky.user.domain.User
import linky.user.dto.AuthenticatedUserBean
import linky.user.find.FindUserCommand
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val pipedNow: PipedNow) {

    @GetMapping("/me")
    fun me(): String? {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth?.principal != null) {
            if (auth.principal is User) {
                return (auth.principal as User).id()
            }
            return auth.principal.toString()
        }
        return null
    }

    @GetMapping("/me/full")
    fun meFull(): AuthenticatedUserBean {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth?.principal != null) {
            if (auth.principal is User) {
                return AuthenticatedUserBean(auth.principal as User)
            }
            val id = auth.principal.toString()
            return FindUserCommand(id).execute(pipedNow)
        }
        return AuthenticatedUserBean()
    }
}