package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface ProductRepository : CrudRepository<Product, Long>
{
//    @Modifying
//    @Query("UPDATE Product p SET p.quantity = ?1 WHERE p.id = ?2")
//    fun updateQuantity(quantity: Int, productId: Long) : Int

    fun findByProductIdAndPrice(productId: Long, price: BigDecimal): Product?
}
