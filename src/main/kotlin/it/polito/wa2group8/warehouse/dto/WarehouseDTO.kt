package it.polito.wa2group8.warehouse.dto

data class WarehouseDTO (
    var id: Long?,

    var name: String?,

    var location: String?,

    var productsIdsList: Set<Long>?,
)
