package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.domain.Product
import it.polito.wa2group8.warehousemvc.dto.ProductDTO
import it.polito.wa2group8.warehousemvc.dto.toProductDTO
import it.polito.wa2group8.warehousemvc.exceptions.BadRequestException
import it.polito.wa2group8.warehousemvc.exceptions.NotFoundException
import it.polito.wa2group8.warehousemvc.repositories.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductServiceImpl(val productRepository: ProductRepository): ProductService
{
    override fun createProduct(productDTO: ProductDTO): ProductDTO?
    {
        if (productDTO.quantity < 0) throw BadRequestException("The quantity must be positive")
        val product = Product(null, productDTO.name, productDTO.category, productDTO.price, productDTO.quantity)
        val createdProduct = productRepository.save(product)
        return createdProduct.toProductDTO()
    }

    override fun updateProduct(quantity: Int, productId: Long): ProductDTO?
    {
        val product = productRepository.findByIdOrNull(productId) ?: throw NotFoundException("Product not found")
        val newQuantity = product.quantity + quantity
        if (newQuantity < 0) throw BadRequestException("Resulting quantity is negative")
        productRepository.updateQuantity(newQuantity,productId)
        product.quantity=newQuantity
        return product.toProductDTO()
    }

    override fun retrieveProduct(id: Long): ProductDTO?
    {
        val product = productRepository.findByIdOrNull(id) ?: throw NotFoundException("Product not found")
        return product.toProductDTO()
    }

    override fun retrieveAllProducts(): Set<ProductDTO>
    {
        return productRepository.findAll().map { it.toProductDTO() }.toSet()
    }

    override fun retrieveProductsByCategory(category: String): Set<ProductDTO>
    {
        val productsByCategory = productRepository.findByCategory(category)
        if (productsByCategory.count() == 0)
            throw NotFoundException("Category not found")
        return productsByCategory.map { it.toProductDTO() }.toSet()
    }
}
