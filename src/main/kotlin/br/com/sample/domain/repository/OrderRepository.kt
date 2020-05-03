package br.com.sample.domain.repository

import br.com.sample.domain.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface OrderRepository {

    fun findById(id: UUID): Order

    fun findAll(pageable: Pageable): Page<Order>

    fun save(order: Order)
}