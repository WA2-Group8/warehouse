package it.polito.wa2group8.warehouse.controllers

import it.polito.wa2group8.warehouse.dto.WarehouseDTO
import it.polito.wa2group8.warehouse.services.WarehouseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class WarehouseController(val warehouseService: WarehouseService)
{
    @GetMapping(value = ["/warehouses"])
    fun getWarehouses() : ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(warehouseService.getWarehouses())
    }

    @GetMapping(value = ["/warehouses/{warehouseID}"])
    fun getWarehouseById(
        @PathVariable warehouseID: Long
    ): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(warehouseService.getWarehouseById(warehouseID))
    }

    @PostMapping(value = ["/warehouses"])
    fun createWarehouse(
        @RequestBody @Valid warehouseDTO: WarehouseDTO
    ): ResponseEntity<Any>
    {
        return ResponseEntity.status(201).body(warehouseService.createOrUpdateWarehouse(null, warehouseDTO))
    }

    @PutMapping(value = ["/warehouses/{warehouseID}"])
    fun createOrUpdateWarehouse(
        @PathVariable warehouseID: Long,
        @RequestBody @Valid warehouseDTO: WarehouseDTO
    ): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(warehouseService.createOrUpdateWarehouse(warehouseID, warehouseDTO))
    }

    @PatchMapping(value = ["/warehouses/{warehouseID}"])
    fun updateWarehouse(
        @PathVariable warehouseID: Long,
        @RequestBody @Valid warehouseDTO: WarehouseDTO
    ): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(warehouseService.updateWarehouse(warehouseID, warehouseDTO))
    }

    @DeleteMapping(value = ["/warehouses/{warehouseID}"])
    fun deleteWarehouse(
        @PathVariable warehouseID: Long
    ): ResponseEntity<Any>
    {
        warehouseService.deleteWarehouse(warehouseID)
        return ResponseEntity.noContent().build()
    }

}