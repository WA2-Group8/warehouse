package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.dto.ProductDTO


interface ProductService {

    fun createProduct(product: ProductDTO): ProductDTO?
    fun updateProduct(product: ProductDTO): ProductDTO?
    fun retrieveProduct(id: Long): ProductDTO?
    fun retrieveAllProducts(): Set<ProductDTO>
    fun retrieveProductsByCategory(category: String): Set<ProductDTO>

}