package br.com.sample.domain.service

import br.com.sample.domain.Order
import br.com.sample.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface OrderService {

    fun create(product: Product): UUID

    fun addProduct(id: UUID, product: Product)

    fun complete(id: UUID)

    fun deleteProduct(id: UUID, productId: UUID)

    fun retrieve(id: UUID): Order

    fun retrieveAll(pageable: Pageable): Page<Order>
}