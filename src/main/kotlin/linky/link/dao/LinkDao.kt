package linky.link.dao

import linky.link.domain.Link
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LinkDao : PagingAndSortingRepository<Link, UUID>, JpaSpecificationExecutor<Link> {
    fun findByCreatedBy(createdBy: String): Collection<Link>
    fun findByName(name: String): Optional<Link>
}