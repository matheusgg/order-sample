package br.com.sample.infrastructure.mongo

import br.com.sample.domain.Order
import br.com.sample.domain.Product

fun Order.toMongoOrder() = MongoOrder(
    id = this.id,
    price = this.price,
    status = this.status,
    orderItems = this.orderItems.map { MongoOrderItem(it.productId, it.price, it.name) }
)

fun MongoOrder.toOrder() =
    Order(this.id, this.status, this.orderItems.map { Product(it.productId, it.price, it.name) })