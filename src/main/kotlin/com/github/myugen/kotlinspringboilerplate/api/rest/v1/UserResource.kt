package com.github.myugen.kotlinspringboilerplate.api.rest.v1

import com.github.myugen.kotlinspringboilerplate.model.User
import com.github.myugen.kotlinspringboilerplate.service.UserUseCase
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/users")
class UserResource(private val userUseCase: UserUseCase) {

    @PostMapping
    fun create(@RequestBody body: UserRequest): ResponseEntity<UserResponse> {
        val user = User.fromRequest(body)
        val createdUser = userUseCase.create(user)
        return ok().body(UserResponse.fromDomain(createdUser))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody body: UserRequest): ResponseEntity<UserResponse> {
        val user = User.fromRequest(body)
        user.id = User.Id(UUID.fromString(id))
        val updatedUser = userUseCase.update(user)
        return ok().body(UserResponse.fromDomain(updatedUser))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        val userId = UUID.fromString(id)
        userUseCase.delete(User.Id(userId))
        return ok().build()
    }

    @GetMapping("{id}")
    fun find(@PathVariable id: String): UserResponse {
        val userId = UUID.fromString(id)
        val foundUser = userUseCase.find(User.Id(userId))
        return UserResponse.fromDomain(foundUser)
    }

    @GetMapping
    fun query(pageable: Pageable): ResponseEntity<List<UserResponse>> {
        val users = userUseCase.query(pageable)
        return ok(users.map { user -> UserResponse.fromDomain(user) }.content)
    }
}

class UserRequest(
    val username: String,
    val name: String,
    val email: String
)

class UserResponse(
    val id: UUID?,
    val username: String,
    val name: String,
    val email: String
) {
    companion object Factory {
        fun fromDomain(domain: User) = UserResponse(domain.id.id, domain.username, domain.name, domain.email)
    }
}
