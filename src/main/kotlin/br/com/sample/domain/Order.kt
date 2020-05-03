package br.com.sample.domain

import br.com.sample.domain.OrderStatus.COMPLETED
import br.com.sample.domain.OrderStatus.CREATED
import br.com.sample.domain.exception.IllegalOrderChangeException
import java.math.BigDecimal.ZERO
import java.util.*

/**
 * Aggregate Root
 */
class Order(val id: UUID, product: Product) {
    var status = CREATED
        private set(value) {
            field = value
        }
    var price = product.price
        private set(value) {
            field = value
        }
    var orderItems = listOf(OrderItem(product))
        private set(value) {
            field = value
        }

    constructor(id: UUID, status: OrderStatus, products: List<Product>) : this(
        id,
        products.first()
    ) {
        var price = ZERO
        val items = mutableListOf<OrderItem>()
        products.forEach {
            price += it.price
            items.add(OrderItem(it))
        }
        this.price = price
        this.status = status
        this.orderItems = items.toList()
    }

    private fun validateState() {
        if (COMPLETED == this.status) {
            throw IllegalOrderChangeException("Order with completed status cannot be changed!")
        }
    }

    fun complete() {
        this.validateState()
        this.status = COMPLETED
    }

    fun addOrderItem(product: Product) {
        this.validateState()
        val items = this.orderItems.toMutableList()
        items.add(OrderItem(product))
        this.orderItems = items.toList()
        this.price += product.price
    }

    fun removeOrderItem(productId: UUID) {
        this.validateState()
        this.orderItems.find { it.productId == productId }?.let {
            val items = this.orderItems.toMutableList()
            items.remove(it)
            this.orderItems = items.toList()
            this.price -= it.price
        }
    }
}