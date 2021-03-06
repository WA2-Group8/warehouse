package it.polito.wa2group8.warehousemvc.repositories

import it.polito.wa2group8.warehousemvc.domain.Product
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Long>
{
    @Modifying
    @Query("UPDATE Product p SET p.quantity = ?1 WHERE p.id = ?2")
    fun updateQuantity(quantity: Int, productId: Long) : Int

    fun findByCategory(category: String): Iterable<Product>
}
