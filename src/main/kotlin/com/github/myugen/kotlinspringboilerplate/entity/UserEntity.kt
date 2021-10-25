package com.github.myugen.kotlinspringboilerplate.entity

import com.github.myugen.kotlinspringboilerplate.model.User
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class UserEntity(
    val id: UUID?,
    @Column(nullable = false) val username: String,
    @Column(nullable = false) val name: String,
    @Column(nullable = false) val mail: String
) : JpaAuditable<UUID>(id) {
    companion object Factory {
        fun fromModel(model: User): UserEntity {
            return UserEntity(model.id, model.username, model.name, model.email)
        }
    }
}
