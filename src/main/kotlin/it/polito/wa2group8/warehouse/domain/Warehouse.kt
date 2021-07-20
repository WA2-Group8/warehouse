package it.polito.wa2group8.warehouse.domain

import it.polito.wa2group8.warehouse.dto.WarehouseDTO
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
class Warehouse (
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        var id: Long?,

        @get:NotEmpty
        var name: String,

        @get:NotEmpty
        var location: String

){
        @OneToMany(mappedBy = "warehouse", targetEntity = ProductStore::class, fetch = FetchType.LAZY)
        var products: MutableList<ProductStore> = mutableListOf()

        fun toWarehouseDTO() = WarehouseDTO(id, name, location)
}