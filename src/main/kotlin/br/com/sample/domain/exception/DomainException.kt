package br.com.sample.domain.exception

open class DomainException(override val message: String) : IllegalStateException(message)