package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Product
import it.polito.wa2group8.warehouse.domain.ProductStore
import it.polito.wa2group8.warehouse.domain.ProductStoreKey
import org.springframework.data.repository.CrudRepository

interface ProductStoreRepository : CrudRepository<ProductStore, ProductStoreKey> {
    fun getByProduct(product: Product) : Iterable<ProductStore>
}