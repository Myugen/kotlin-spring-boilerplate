package com.github.myugen.kotlinspringboilerplate.model

import javax.validation.ConstraintViolationException
import javax.validation.Validation

interface Identifiable<T> {
    val id: T?
}

@Suppress("UNCHECKED_CAST")
sealed class SelfValidating<T> {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    fun validate() {
        val violations = validator.validate(this as T)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
