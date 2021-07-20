package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.WarehouseOutbox
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WarehouseOutboxRepository : CrudRepository<WarehouseOutbox, Long>
{
    fun findByOrderId(orderId: Long): WarehouseOutbox?
}
