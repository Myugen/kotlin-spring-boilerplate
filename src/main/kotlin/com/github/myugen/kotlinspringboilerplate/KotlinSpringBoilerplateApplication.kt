package com.github.myugen.kotlinspringboilerplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableWebSecurity
@SpringBootApplication
class KotlinSpringBoilerplateApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringBoilerplateApplication>(*args)
}
