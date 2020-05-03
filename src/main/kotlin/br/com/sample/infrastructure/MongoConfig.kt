package br.com.sample.infrastructure

import br.com.sample.infrastructure.mongo.repository.SpringDataMongoOrderRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackageClasses = [SpringDataMongoOrderRepository::class])
class MongoConfig(
    private val mongoTemplate: MongoTemplate,
    private val mongoConverter: MongoConverter
) {

    @EventListener(ApplicationReadyEvent::class)
    fun initIndicesAfterStartup() {
        val context = this.mongoConverter.mappingContext
        if (context is MongoMappingContext) {
            context.persistentEntities
                .forEach {
                    val type = it.type
                    if (type.isAnnotationPresent(Document::class.java)) {
                        val resolver = MongoPersistentEntityIndexResolver(context)
                        val indexOps = this.mongoTemplate.indexOps(type)
                        resolver.resolveIndexFor(type).forEach { def -> indexOps.ensureIndex(def) }
                    }
                }
        }
    }
}