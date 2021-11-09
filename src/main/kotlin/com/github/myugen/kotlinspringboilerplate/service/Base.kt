package com.github.myugen.kotlinspringboilerplate.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CrudUseCase<M, ID> {
    fun create(model: M): M
    fun update(model: M): M
    fun delete(uid: ID)
    fun query(page: Pageable): Page<M>
    fun find(uid: ID): M
}