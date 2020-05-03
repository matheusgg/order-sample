package br.com.sample.domain.exception

class IllegalOrderChangeException(override val message: String) : DomainException(message)