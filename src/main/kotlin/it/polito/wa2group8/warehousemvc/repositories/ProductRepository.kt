package it.polito.wa2group8.warehousemvc.repositories

import it.polito.wa2group8.warehousemvc.domain.Product
import org.springframework.data.repository.CrudRepository

import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product,Long> {

}