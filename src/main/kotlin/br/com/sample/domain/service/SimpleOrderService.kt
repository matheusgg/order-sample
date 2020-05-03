package br.com.sample.domain.service

import br.com.sample.domain.Order
import br.com.sample.domain.Product
import br.com.sample.domain.repository.OrderRepository
import org.springframework.data.domain.Pageable
import java.util.*
import java.util.UUID.randomUUID

/**
 * Inbound Port
 */
class SimpleOrderService(private val repository: OrderRepository) : OrderService {

    override fun create(product: Product): UUID {
        val order = Order(randomUUID(), product)
        this.repository.save(order)
        return order.id
    }

    override fun addProduct(id: UUID, product: Product) {
        this.retrieve(id).let {
            it.addOrderItem(product)
            this.repository.save(it)
        }
    }

    override fun complete(id: UUID) {
        this.retrieve(id).let {
            it.complete()
            this.repository.save(it)
        }
    }

    override fun deleteProduct(id: UUID, productId: UUID) {
        this.retrieve(id).let {
            it.removeOrderItem(productId)
            this.repository.save(it)
        }
    }

    override fun retrieve(id: UUID) = this.repository.findById(id)

    override fun retrieveAll(pageable: Pageable) = this.repository.findAll(pageable)
}