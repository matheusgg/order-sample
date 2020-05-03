package br.com.sample.infrastructure.mongo.repository

import br.com.sample.infrastructure.mongo.MongoOrder
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SpringDataMongoOrderRepository : MongoRepository<MongoOrder, UUID>