package linky.user

import linky.user.domain.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserDao: PagingAndSortingRepository<User, UUID> {
    fun findByEmail(email: String): User?
}