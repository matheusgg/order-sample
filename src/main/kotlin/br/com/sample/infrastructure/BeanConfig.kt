package br.com.sample.infrastructure

import br.com.sample.domain.repository.OrderRepository
import br.com.sample.domain.service.SimpleOrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    fun orderService(repository: OrderRepository) = SimpleOrderService(repository)
}