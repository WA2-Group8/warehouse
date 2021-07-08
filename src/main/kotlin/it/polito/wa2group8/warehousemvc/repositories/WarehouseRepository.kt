package it.polito.wa2group8.warehousemvc.repositories

import it.polito.wa2group8.warehousemvc.domain.Warehouse
import org.springframework.data.repository.CrudRepository

interface WarehouseRepository : CrudRepository<Warehouse, Long> {

}