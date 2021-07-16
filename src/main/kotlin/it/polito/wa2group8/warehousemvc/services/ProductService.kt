package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.dto.ProductDTO
import it.polito.wa2group8.warehousemvc.dto.WarehouseDTO

interface ProductService
{
    fun getProducts(category: String?): Set<ProductDTO>
    fun getProductById(id: Long): ProductDTO?
    fun createOrUpdateProduct(productID: Long?, productDTO: ProductDTO) : ProductDTO?
    fun updateProduct(productID: Long, productDTO: ProductDTO) : ProductDTO?
    fun deleteProduct(id: Long)
    fun getProductPicture(id: Long) : String
    fun addProductPicture(id: Long) : String
    fun getProductWarehouses(id: Long) : Set<WarehouseDTO>
}
