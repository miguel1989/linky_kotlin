package linky.user.domain

import linky.domain.BaseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.ArrayList
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Column(name = "email", unique = true, length = 120)
        private var email: String = "",

        @Column(name = "password")
        private var password: String = "",

        @Column(name = "name")
        private var name: String = "",

        @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
        private var roles: MutableList<Role> = mutableListOf<Role>()
) : BaseEntity(), UserDetails {

    init {
        grantUser()
    }

    fun email(): String {
        return this.email
    }

    fun password(): String {
        return this.password
    }

    fun name(): String {
        return this.name
    }

    fun grantUser() {
        this.roles.add(Role(this, Roles.ROLE_USER.toString()))
    }

    fun grantAdmin() {
        this.roles.add(Role(this, Roles.ROLE_ADMIN.toString()))
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}