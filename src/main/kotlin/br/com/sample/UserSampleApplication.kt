package br.com.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserSampleApplication

fun main(args: Array<String>) {
    runApplication<UserSampleApplication>(*args)
}
