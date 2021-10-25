package com.github.myugen.kotlinspringboilerplate.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.time.Instant
import javax.persistence.*

@MappedSuperclass
abstract class JpaPersistable<T : Serializable>(
    @Id
    @GeneratedValue
    private var id: T?
) {

    companion object {
        private val serialVersionUID = -5554308939380869754L
    }

    fun getId(): T? {
        return id
    }

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as JpaPersistable<*>

        return if (null == this.getId()) false else this.getId() == other.getId()
    }

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"

}

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class JpaAuditable<T : Serializable>(id: T?) : JpaPersistable<T>(id) {
    @Column(nullable = false, updatable = false)
    @CreatedDate
    val createdOn: Instant = Instant.now()

    @Column(nullable = true, updatable = false)
    @CreatedBy
    val createdBy: String? = null

    @Column(nullable = true)
    @LastModifiedDate
    val modifiedOn: Instant = Instant.now()

    @Column(nullable = true)
    @LastModifiedBy
    val modifiedBy: String? = null
}