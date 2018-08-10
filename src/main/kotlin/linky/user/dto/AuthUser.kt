package linky.user.dto;

import linky.user.domain.User
import org.springframework.security.core.context.SecurityContextHolder

class AuthUser {
    private var id: String = ""
    init {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth?.principal != null) {
            if (auth.principal is User) {
                this.id = (auth.principal as User).id()
            }
            this.id = auth.principal.toString()
        }
    }

    fun id(): String {
        return this.id
    }
}
