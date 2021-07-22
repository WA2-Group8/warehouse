package it.polito.wa2group8.warehouse.services

import it.polito.wa2group8.warehouse.domain.Warehouse
import it.polito.wa2group8.warehouse.domain.ProductWarehouse
import it.polito.wa2group8.warehouse.dto.WarehouseDTO
import it.polito.wa2group8.warehouse.dto.WarehouseProductsDTO
import it.polito.wa2group8.warehouse.exceptions.BadRequestException
import it.polito.wa2group8.warehouse.exceptions.NotFoundException
import it.polito.wa2group8.warehouse.repositories.ProductRepository
import it.polito.wa2group8.warehouse.repositories.ProductWarehouseRepository
import it.polito.wa2group8.warehouse.repositories.WarehouseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WarehouseServiceImpl (
    private val warehouseRepository: WarehouseRepository,
    private val productRepository: ProductRepository,
    private val pwRepository: ProductWarehouseRepository,
) : WarehouseService
{
    override fun getWarehouses(): List<WarehouseDTO>
    {
        return warehouseRepository.findAll().map { it.toWarehouseDTO() }
    }

    override fun getWarehouseById(warehouseID: Long): WarehouseDTO?
    {
        val warehouse = warehouseRepository.findByIdOrNull(warehouseID) ?: throw NotFoundException("Warehouse not found")
        return warehouse.toWarehouseDTO()
    }

    override fun createOrUpdateWarehouse(warehouseID: Long?, warehouseDTO: WarehouseDTO): WarehouseDTO?
    {
        //Check that parameters were not null
        //We cannot use validation because of the PATCH method
        if (warehouseDTO.name == null)
            throw BadRequestException("Name field cannot be empty")
        if (warehouseDTO.location == null)
            throw BadRequestException("Location field cannot be empty")

        return try
        {
            //Try to update an existing warehouse (full representation thanks to previous checks)
            updateWarehouse(warehouseID ?: -1, warehouseDTO)
        }
        catch (e: NotFoundException)
        {
            //If here, we want to insert a new warehouse since warehouse doesn't exist
            val createdWarehouse = warehouseRepository.save(Warehouse(null, warehouseDTO.name, warehouseDTO.location))
            createdWarehouse.toWarehouseDTO()
        }
    }

    override fun updateWarehouse(warehouseID: Long, warehouseDTO: WarehouseDTO): WarehouseDTO?
    {
        val warehouse = warehouseRepository.findByIdOrNull(warehouseID) ?: throw NotFoundException("Warehouse not found")
        warehouse.name = warehouseDTO.name ?: warehouse.name
        warehouse.location = warehouseDTO.location ?: warehouse.location
        val updatedWarehouse = warehouseRepository.save(warehouse)
        return updatedWarehouse.toWarehouseDTO()
    }

    override fun deleteWarehouse(warehouseID: Long)
    {
        warehouseRepository.findByIdOrNull(warehouseID) ?: throw NotFoundException("Warehouse not found")
        //Delete product warehouse associated to the warehouse
        pwRepository.deleteAllByWarehouseId(warehouseID)
        //LASTLY (in order to don't violate UK constraint), delete warehouse
        warehouseRepository.deleteById(warehouseID)
    }

    override fun addProducts(warehouseID: Long, warehouseProductsDTO: WarehouseProductsDTO): List<Long>
    {
        val warehouse = warehouseRepository.findByIdOrNull(warehouseID) ?: throw NotFoundException("Warehouse not found")
        val validIds: MutableList<Long> = mutableListOf()
        for (it in warehouseProductsDTO.products)
        {
            val product = productRepository.findByIdOrNull(it.productID) ?: continue
            var pw = pwRepository.findByWarehouseIdAndProductId(warehouseID, it.productID)
            if (pw == null)
            {
                //If here, warehouse doesnt not contain the product yet
                if (it.alarmOnQuantity == null || it.alarmOnQuantity < 1) continue
                if (it.quantity < 0) continue
                pw = ProductWarehouse(null, product, warehouse, it.quantity, it.alarmOnQuantity)
                pwRepository.save(pw)
            }
            else
            {
                //If here, the warehouse already contains the product
                //Before update quantity, check that the new quantity isn't negative
                if (pw.quantity + it.quantity < 0) continue
                pwRepository.incrementQuantity(it.quantity, warehouseID, it.productID)
            }
            //The product was added/removed
            validIds.add(it.productID)
        }
        return validIds
    }
}
