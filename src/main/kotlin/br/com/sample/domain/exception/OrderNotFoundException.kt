package br.com.sample.domain.exception

class OrderNotFoundException(override val message: String) : DomainException(message)