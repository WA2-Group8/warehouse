package it.polito.wa2group8.warehousemvc.repositories

import it.polito.wa2group8.warehousemvc.domain.ProductStore
import it.polito.wa2group8.warehousemvc.domain.ProductStoreKey
import org.springframework.data.repository.CrudRepository

interface ProductStoreRepository : CrudRepository<ProductStore, ProductStoreKey> {
}