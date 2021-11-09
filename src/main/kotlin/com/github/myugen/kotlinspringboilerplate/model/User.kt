package com.github.myugen.kotlinspringboilerplate.model

import com.github.myugen.kotlinspringboilerplate.api.rest.v1.UserRequest
import com.github.myugen.kotlinspringboilerplate.entity.UserEntity
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class User(
    var id: Id,
    @NotBlank var username: String,
    @NotBlank var name: String,
    @NotBlank @Email var email: String,
) : SelfValidating<User>() {
    class Id(override val id: UUID?) : Identifiable<UUID>

    companion object Factory {
        fun fromEntity(entity: UserEntity) =
            User(Id(entity.id), entity.username, entity.name, entity.mail)

        fun fromRequest(request: UserRequest) = User(Id(null), request.username, request.name, request.email)
    }
}