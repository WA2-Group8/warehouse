package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Product
import it.polito.wa2group8.warehouse.domain.ProductWarehouse
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ProductWarehouseRepository : CrudRepository<ProductWarehouse, Long>
{
    fun findAllByProduct(product: Product): Iterable<ProductWarehouse>

    @Query("SELECT pw FROM ProductWarehouse pw WHERE pw.product.productId = ?1 AND pw.quantity = ?2")
    fun findAllByProductIdAndQuantity(productId: Long, quantity: Int): Iterable<ProductWarehouse>

    @Modifying
    @Query("UPDATE ProductWarehouse pw SET pw.quantity = pw.quantity - ?1 WHERE pw.warehouse.warehouseId = ?2 AND pw.product.productId = ?3")
    fun reduceQuantity(quantity: Int, warehouseId: Long, productId: Long) : Int

    @Modifying
    @Query("UPDATE ProductWarehouse pw SET pw.quantity = pw.quantity + ?1 WHERE pw.warehouse.warehouseId = ?2 AND pw.product.productId = ?3")
    fun incrementQuantity(quantity: Int, warehouseId: Long, productId: Long) : Int
}
