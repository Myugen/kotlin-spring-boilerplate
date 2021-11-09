package com.github.myugen.kotlinspringboilerplate.entity

import com.github.myugen.kotlinspringboilerplate.model.User
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Type(type = "pg-uuid") @Column(length = 36) val id: UUID?,
    @Column(nullable = false, unique = true) val username: String,
    @Column(nullable = false) val name: String,
    @Column(nullable = false, unique = true) val mail: String
) : JpaAuditable<UUID>(id) {
    companion object Factory {
        fun fromModel(model: User): UserEntity {
            return UserEntity(model.id.id, model.username, model.name, model.email)
        }
    }
}
