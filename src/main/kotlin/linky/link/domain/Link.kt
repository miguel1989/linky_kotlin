package linky.link.domain

import linky.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "links", indexes = [(Index(name = "idx_name", columnList = "name"))])
class Link(
        @Column(name = "name", unique = true)
        private var name: String = "",

        @Column(name = "url")
        private var url: String = "",

        @Column(name = "created_by")
        private var createdBy: String = ""
): BaseEntity() {
    fun name(): String {
        return this.name
    }
    fun url(): String {
        return this.url
    }
}