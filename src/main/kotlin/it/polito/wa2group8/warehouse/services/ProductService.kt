package it.polito.wa2group8.warehouse.services

import it.polito.wa2group8.warehouse.dto.CommentDTO
import it.polito.wa2group8.warehouse.dto.ProductDTO
import it.polito.wa2group8.warehouse.dto.WarehouseDTO
import org.springframework.web.multipart.MultipartFile

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
    fun getProduct(productID: Long): ProductDTO?

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
     * Get the list of the warehouses that contain the product identified by productID
     */
    fun getProductWarehouses(productID: Long): List<WarehouseDTO>

    /**
     * Add a comment to the product identified by productID
     */
    fun addComment(productID: Long, commentDTO: CommentDTO)

    /**
     * Updates the picture of the product identified by productID
     */
    fun setProductPicture(productID: Long, file: MultipartFile)

    /**
     * Retrieves the picture of the product identified by productID
     */
    fun getProductPicture(productID: Long): ByteArray

    fun getPictureAndPictureURL(productID: Long): Pair<ByteArray, String>
}
