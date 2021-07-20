package it.polito.wa2group8.warehouse.services

import it.polito.wa2group8.warehouse.dto.ProductDTO
import it.polito.wa2group8.warehouse.dto.WarehouseDTO

interface ProductService
{
    fun getProducts(category: String?): Set<ProductDTO>
    fun getProductById(id: Long): ProductDTO?
    fun createOrUpdateProduct(productID: Long?, productDTO: ProductDTO) : ProductDTO?
    fun updateProduct(productID: Long, productDTO: ProductDTO) : ProductDTO?
    fun deleteProduct(id: Long)
    fun getProductPicture(id: Long) : String
    fun addProductPicture(id: Long) : String
    fun getProductWarehouses(id: Long) : List<WarehouseDTO>
}
