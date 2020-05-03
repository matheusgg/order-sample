package br.com.sample.domain

class OrderItem(product: Product) {
    val productId = product.id
    val price = product.price
    val name = product.name
}