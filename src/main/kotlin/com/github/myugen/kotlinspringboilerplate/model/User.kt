package com.github.myugen.kotlinspringboilerplate.model

import com.github.myugen.kotlinspringboilerplate.entity.UserEntity
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class User(
    override val id: UUID?,
    @NotBlank val username: String,
    @NotBlank val name: String,
    @NotBlank @Email val email: String,
) : SelfValidating<User>(), Identifiable {
    companion object Factory {
        fun fromEntity(userEntity: UserEntity) =
            User(userEntity.id, userEntity.username, userEntity.name, userEntity.mail)
    }
}