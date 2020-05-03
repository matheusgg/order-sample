package br.com.sample.infrastructure.mongo.repository

import br.com.sample.domain.Order
import br.com.sample.domain.exception.OrderNotFoundException
import br.com.sample.domain.repository.OrderRepository
import br.com.sample.infrastructure.mongo.toMongoOrder
import br.com.sample.infrastructure.mongo.toOrder
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

/**
 * Outbound Adapter
 */
@Component
class MongoOrderRepository(private val mongoRepository: SpringDataMongoOrderRepository) :
    OrderRepository {

    override fun findById(id: UUID) =
        this.mongoRepository.findByIdOrNull(id)?.toOrder()
            ?: throw OrderNotFoundException("Order not found!")

    override fun findAll(pageable: Pageable) = this.mongoRepository.findAll(pageable)
        .map { it.toOrder() }

    override fun save(order: Order) {
        this.mongoRepository.save(order.toMongoOrder())
    }
}