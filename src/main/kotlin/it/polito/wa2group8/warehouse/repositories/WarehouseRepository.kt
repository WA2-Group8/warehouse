package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Warehouse
import org.springframework.data.repository.CrudRepository

interface WarehouseRepository : CrudRepository<Warehouse, Long> {

}