package br.com.sample.infrastructure.mongo

import br.com.sample.domain.OrderStatus
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document("order")
data class MongoOrder(
    val id: UUID,
    @Indexed
    val price: BigDecimal,
    val status: OrderStatus,
    var orderItems: List<MongoOrderItem>
)
