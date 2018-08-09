package linky.user.dto

import org.springframework.security.core.GrantedAuthority

class RoleBean(val value: String): GrantedAuthority {
    override fun getAuthority(): String {
        return value
    }
}