package linky.user.dto

import linky.infra.command.Return
import linky.user.domain.User

class FullUserBean(
        val id: String = "",
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val roles: MutableList<RoleBean> = mutableListOf<RoleBean>()
): Return {
    constructor(user: User) : this(user.id(), user.name(), user.email(), user.password(), mutableListOf<RoleBean>()) {
        roles.addAll(user.authorities.map { role -> RoleBean(role.authority) })
    }
}