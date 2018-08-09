package linky.user.domain

import linky.domain.BaseEntity
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name="roles")
class Role(
        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private val user: User = User(),

        @Column(name = "role", length = 120)
        private val role: String = ""
): BaseEntity(), GrantedAuthority {

    fun user(): User {
        return this.user
    }

    override fun getAuthority(): String {
        return this.role
    }
}