package br.com.sample.infrastructure.mongo

import java.math.BigDecimal
import java.util.*

data class MongoOrderItem(val productId: UUID, val price: BigDecimal, val name: String)