package linky.domain

import com.datastax.driver.core.utils.UUIDs
import java.time.LocalTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {
    @Id @Column(name = "id") protected var id:UUID = UUIDs.timeBased()
    @Column(name = "created_at") protected var createdAt: LocalTime = LocalTime.now()

    fun id():String {
        return this.id.toString()
    }
}