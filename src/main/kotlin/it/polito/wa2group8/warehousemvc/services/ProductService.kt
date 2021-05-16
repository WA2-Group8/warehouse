package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.dto.ProductDTO

interface ProductService
{
    fun createProduct(productDTO: ProductDTO): ProductDTO?
    fun updateProduct(quantity: Int, productId: Long): ProductDTO?
    fun retrieveProduct(id: Long): ProductDTO?
    fun retrieveAllProducts(): Set<ProductDTO>
    fun retrieveProductsByCategory(category: String): Set<ProductDTO>
}
