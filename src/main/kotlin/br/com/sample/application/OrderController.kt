package br.com.sample.application

import br.com.sample.application.vo.AddProductRequest
import br.com.sample.application.vo.CreateOrderRequest
import br.com.sample.application.vo.CreateOrderResponse
import br.com.sample.application.vo.RetrieveOrderResponse
import br.com.sample.domain.service.OrderService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/orders")
class OrderController(private val service: OrderService) {

    @PostMapping
    fun create(@RequestBody request: CreateOrderRequest): CreateOrderResponse {
        val id = this.service.create(request.product)
        return CreateOrderResponse(id)
    }

    @GetMapping
    fun retrieveAll(@PageableDefault pageable: Pageable) = this.service.retrieveAll(pageable)
        .map { RetrieveOrderResponse(it) }

    @GetMapping("/{id}")
    fun retrieve(@PathVariable id: UUID) = RetrieveOrderResponse(this.service.retrieve(id))

    @PostMapping("/{id}/complete")
    fun complete(@PathVariable id: UUID) = this.service.complete(id)

    @PostMapping("/{id}/products")
    fun addProduct(@PathVariable id: UUID, @RequestBody request: AddProductRequest) =
        this.service.addProduct(id, request.product)

    @DeleteMapping("/{id}/products/{productId}")
    fun deleteProduct(@PathVariable id: UUID, @PathVariable productId: UUID) =
        this.service.deleteProduct(id, productId)
}