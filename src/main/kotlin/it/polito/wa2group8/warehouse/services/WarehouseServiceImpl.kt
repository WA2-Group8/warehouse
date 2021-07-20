package it.polito.wa2group8.warehouse.services

import it.polito.wa2group8.warehouse.domain.Warehouse
import it.polito.wa2group8.warehouse.dto.WarehouseDTO
import it.polito.wa2group8.warehouse.exceptions.BadRequestException
import it.polito.wa2group8.warehouse.exceptions.NotFoundException
import it.polito.wa2group8.warehouse.repositories.WarehouseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WarehouseServiceImpl (private val warehouseRepository: WarehouseRepository) : WarehouseService
{
    override fun getWarehouses(): List<WarehouseDTO>
    {
        return warehouseRepository.findAll().map { it.toWarehouseDTO() }
    }

    override fun getWarehouseById(id: Long): WarehouseDTO?
    {
        val warehouse = warehouseRepository.findByIdOrNull(id) ?: throw NotFoundException("Warehouse not found")
        return warehouse.toWarehouseDTO()
    }

    override fun createOrUpdateWarehouse(warehouseID: Long?, warehouseDTO: WarehouseDTO): WarehouseDTO?
    {
        // check that parameters ware not null
        // cannot use validation because of the PATCH method
        if (warehouseDTO.name == null) throw BadRequestException("Name field cannot be empty")
        if (warehouseDTO.location == null) throw BadRequestException("Location field cannot be empty")

        val warehouse = Warehouse(warehouseID, warehouseDTO.name!!, warehouseDTO.location!!)
        val createdWarehouse = warehouseRepository.save(warehouse)
        return createdWarehouse.toWarehouseDTO()
    }

    override fun updateWarehouse(warehouseID: Long, warehouseDTO: WarehouseDTO): WarehouseDTO?
    {
        val warehouse = warehouseRepository.findByIdOrNull(warehouseID) ?: throw NotFoundException("Warehouse not found")
        warehouse.name = warehouseDTO.name ?: warehouse.name
        warehouse.location = warehouseDTO.location ?: warehouse.location
        val updatedWarehouse = warehouseRepository.save(warehouse)
        return updatedWarehouse.toWarehouseDTO()
    }

    override fun deleteWarehouse(id: Long)
    {
        warehouseRepository.findByIdOrNull(id) ?: throw NotFoundException("Warehouse not found")
        warehouseRepository.deleteById(id)
    }
}
