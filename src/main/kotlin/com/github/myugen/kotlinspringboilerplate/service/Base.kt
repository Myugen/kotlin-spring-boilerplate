package com.github.myugen.kotlinspringboilerplate.service

import com.github.myugen.kotlinspringboilerplate.model.Identifiable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CrudUseCase<M : Identifiable> {
    fun create(model: M): M
    fun update(model: M): M
    fun delete(identifiable: Identifiable)
    fun query(page: Pageable): Page<M>
    fun find(identifiable: Identifiable): M
}