package com.github.myugen.kotlinspringboilerplate.repository

import com.github.myugen.kotlinspringboilerplate.entity.UserEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : PagingAndSortingRepository<UserEntity, UUID> {
    fun findByUsername(username: String): UserEntity?
}