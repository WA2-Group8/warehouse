package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.dto.ProductDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductServiceImpl: ProductService {
    override fun createProduct(product: ProductDTO): ProductDTO? {
        TODO("Not yet implemented")
    }

    override fun updateProduct(product: ProductDTO): ProductDTO? {
        TODO("Not yet implemented")
    }

    override fun retrieveProduct(id: Long): ProductDTO? {
        TODO("Not yet implemented")
    }

    override fun retrieveAllProducts(): Set<ProductDTO> {
        TODO("Not yet implemented")
    }

    override fun retrieveProductsByCategory(category: String): Set<ProductDTO> {
        TODO("Not yet implemented")
    }

}