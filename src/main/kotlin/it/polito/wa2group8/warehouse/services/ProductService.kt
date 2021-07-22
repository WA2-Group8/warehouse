package it.polito.wa2group8.warehouse.services

import it.polito.wa2group8.warehouse.dto.CommentDTO
import it.polito.wa2group8.warehouse.dto.ProductDTO
import it.polito.wa2group8.warehouse.dto.WarehouseDTO

interface ProductService
{
    /**
     * Retrieves the list of all products.
     * Specifying yhe category, retrieves all products by a given category
     */
    fun getProducts(category: String?): List<ProductDTO>

    /**
     * Retrieves the product identified by productID
     */
    fun getProductById(productID: Long): ProductDTO?

    /**
     * Updates an existing product full representation or add a new one if not exists
     */
    fun createOrUpdateProduct(productID: Long?, productDTO: ProductDTO): ProductDTO?

    /**
     * Updates an existing product (partial representation)
     */
    fun updateProduct(productID: Long, productDTO: ProductDTO): ProductDTO?

    /**
     * Deletes the product identified by productID
     */
    fun deleteProduct(productID: Long)

    /**
     * Retrieves the picture of the product identified by productID
     */
    fun getProductPicture(productID: Long): String

    /**
     * Updates the picture of the product identified by productID
     */
    fun addProductPicture(productID: Long): String

    /**
     * Get the list of the warehouses that contain the product identified by productID
     */
    fun getProductWarehouses(productID: Long): List<WarehouseDTO>

    /**
     * Add a comment to the product identified by productID
     */
    fun addComment(productID: Long, commentDTO: CommentDTO)
}
