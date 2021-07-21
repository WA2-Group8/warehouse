package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Product
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*
import javax.persistence.LockModeType

@Repository
interface ProductRepository : CrudRepository<Product, Long>
{
//    @Modifying
//    @Query("UPDATE Product p SET p.quantity = ?1 WHERE p.id = ?2")
//    fun updateQuantity(quantity: Int, productId: Long) : Int

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findById(id: Long): Optional<Product>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findAll(): MutableIterable<Product>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun <S : Product?> save(entity: S): S

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByProductIdAndPrice(productId: Long, price: BigDecimal): Product?

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByCategory(category: String): Iterable<Product>
}
