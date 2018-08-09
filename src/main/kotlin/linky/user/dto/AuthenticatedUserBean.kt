package linky.user.dto

import linky.infra.command.Return
import linky.user.domain.User

class AuthenticatedUserBean(
        val id: String = "",
        val name: String = "",
        val email: String = "",
        val roles: MutableList<RoleBean>) : Return {
    constructor(user: User) : this(user.id(), user.name(), user.email(), mutableListOf<RoleBean>()) {
        roles.addAll(user.authorities.map { role -> RoleBean(role.authority) })
    }
}