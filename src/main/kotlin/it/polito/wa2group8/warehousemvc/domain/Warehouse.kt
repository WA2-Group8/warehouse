package it.polito.wa2group8.warehousemvc.domain

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Warehouse (
        @Id
        var id: Long?
){
        @OneToMany(mappedBy = "warehouse", targetEntity = ProductStore::class, fetch = FetchType.LAZY)
        var products: MutableList<ProductStore> = mutableListOf()
}