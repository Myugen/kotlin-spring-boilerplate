package com.github.myugen.kotlinspringboilerplate.service

import com.github.myugen.kotlinspringboilerplate.entity.UserEntity
import com.github.myugen.kotlinspringboilerplate.model.Identifiable
import com.github.myugen.kotlinspringboilerplate.model.User
import com.github.myugen.kotlinspringboilerplate.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

interface UserUseCase : CrudUseCase<User>

@Service
class UserService(val userRepository: UserRepository) : UserUseCase {
    override fun create(model: User): User {
        return save(model)
    }

    override fun update(model: User): User {
        return save(model)
    }

    override fun delete(identifiable: Identifiable) {
        userRepository.deleteById(identifiable.id!!)
    }

    override fun query(page: Pageable): Page<User> {
        return userRepository.findAll(page).map(User.Factory::fromEntity)
    }

    override fun find(identifiable: Identifiable): User {
        val entity = userRepository.findById(identifiable.id!!).get()
        return User.fromEntity(entity)
    }

    private fun save(model: User): User {
        model.validate()
        val entity = userRepository.save(UserEntity.fromModel(model))
        return User.fromEntity(entity)
    }
}