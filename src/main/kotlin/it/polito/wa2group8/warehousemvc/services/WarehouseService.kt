package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.dto.WarehouseDTO

interface WarehouseService
{
    fun getWarehouses() : Set<WarehouseDTO>
    fun getWarehouseById(id: Long) : WarehouseDTO?
    fun createOrUpdateWarehouse(warehouseID: Long?, warehouseDTO: WarehouseDTO) : WarehouseDTO?
    fun updateWarehouse(warehouseID: Long, warehouseDTO: WarehouseDTO) : WarehouseDTO?
    fun deleteWarehouse(id: Long)
}