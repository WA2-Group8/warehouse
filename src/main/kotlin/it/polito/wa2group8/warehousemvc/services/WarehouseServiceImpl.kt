package it.polito.wa2group8.warehousemvc.services

import it.polito.wa2group8.warehousemvc.domain.Warehouse
import it.polito.wa2group8.warehousemvc.dto.WarehouseDTO
import it.polito.wa2group8.warehousemvc.exceptions.NotFoundException
import it.polito.wa2group8.warehousemvc.repositories.WarehouseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WarehouseServiceImpl (
    val warehouseRepository: WarehouseRepository
) : WarehouseService
{
    override fun getWarehouses(): Set<WarehouseDTO> {
        return warehouseRepository.findAll().map { it.toWarehouseDTO() }.toSet()
    }

    override fun getWarehouseById(id: Long): WarehouseDTO? {
        val warehouse = warehouseRepository.findByIdOrNull(id) ?: throw NotFoundException("Warehouse not found")
        return warehouse.toWarehouseDTO()
    }

    override fun createOrUpdateWarehouse(warehouseID: Long?, warehouseDTO: WarehouseDTO): WarehouseDTO? {
        val warehouse = Warehouse(warehouseID)
        val createdWarehouse = warehouseRepository.save(warehouse)
        return createdWarehouse.toWarehouseDTO()
    }

    override fun updateWarehouse(warehouseID: Long, warehouseDTO: WarehouseDTO): WarehouseDTO? {
        TODO("Not yet implemented")
    }

    override fun deleteWarehouse(id: Long) {
        val warehouse = warehouseRepository.findByIdOrNull(id) ?: throw NotFoundException("Warehouse not found")
        warehouseRepository.deleteById(id)
    }

}