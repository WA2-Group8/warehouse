package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.domain.Product
import it.polito.wa2group8.warehousemvc.dto.ProductDTO
import it.polito.wa2group8.warehousemvc.dto.WarehouseDTO
import it.polito.wa2group8.warehousemvc.exceptions.BadRequestException
import it.polito.wa2group8.warehousemvc.exceptions.NotFoundException
import it.polito.wa2group8.warehousemvc.mappers.ProductMapper
import it.polito.wa2group8.warehousemvc.repositories.ProductRepository
import it.polito.wa2group8.warehousemvc.repositories.ProductStoreRepository
import it.polito.wa2group8.warehousemvc.repositories.WarehouseRepository
import org.mapstruct.factory.Mappers
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val productStoreRepository: ProductStoreRepository
): ProductService
{
    override fun getProducts(category: String): Set<ProductDTO> {
        return if(category == "")
            productRepository.findAll().map{ it.toProductDTO() }.toSet()
        else
            productRepository.findByCategory(category).map{ it.toProductDTO() }.toSet()
    }

    override fun getProductById(id: Long): ProductDTO? {
        val product = productRepository.findByIdOrNull(id) ?: throw NotFoundException("Product not found")
        return product.toProductDTO()
    }

    override fun createOrUpdateProduct(productID: Long?, productDTO: ProductDTO): ProductDTO?
    {
        // date created here? pictureURL nullable?
        val product = Product(productID, productDTO.name!! , productDTO.description!!, productDTO.pictureURL!!, productDTO.category!!, productDTO.price!!, productDTO.averageRating!!, productDTO.creationDate!!)
        val createdProduct = productRepository.save(product)
        return createdProduct.toProductDTO()
    }

    override fun updateProduct(productID: Long, productDTO: ProductDTO): ProductDTO?
    {
        val product = productRepository.findByIdOrNull(productID) ?: throw NotFoundException("Product not found")
        val productMapper = Mappers.getMapper(ProductMapper::class.java)
        productMapper.updateProductFromDto(productDTO, product)
        val updatedProduct = productRepository.save(product)
        return updatedProduct.toProductDTO()
    }

    override fun deleteProduct(id: Long)
    {
        // return value?
        productRepository.findByIdOrNull(id) ?: throw NotFoundException("Product not found")
        productRepository.deleteById(id)
    }

    override fun getProductPicture(id: Long): String {
        TODO("Not yet implemented")
    }

    override fun addProductPicture(id: Long): String {
        TODO("Not yet implemented")
    }

    override fun getProductWarehouses(id: Long): Set<WarehouseDTO> {
        val product = productRepository.findByIdOrNull(id) ?: throw NotFoundException("Product not found")
        return productStoreRepository.getByProduct(product).map{ it.warehouse.toWarehouseDTO() }.toSet()

    }

}
