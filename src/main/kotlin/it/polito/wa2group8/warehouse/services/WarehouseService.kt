package it.polito.wa2group8.warehouse.services

import it.polito.wa2group8.warehouse.dto.WarehouseDTO

interface WarehouseService
{
    fun getWarehouses() : List<WarehouseDTO>
    fun getWarehouseById(id: Long) : WarehouseDTO?
    fun createOrUpdateWarehouse(warehouseID: Long?, warehouseDTO: WarehouseDTO) : WarehouseDTO?
    fun updateWarehouse(warehouseID: Long, warehouseDTO: WarehouseDTO) : WarehouseDTO?
    fun deleteWarehouse(id: Long)
}