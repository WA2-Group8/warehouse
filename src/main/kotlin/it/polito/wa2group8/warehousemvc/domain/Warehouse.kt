package it.polito.wa2group8.warehousemvc.domain

import javax.persistence.*

@Entity
class Warehouse (
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        var id: Long?
){
        @OneToMany(mappedBy = "warehouse", targetEntity = ProductStore::class, fetch = FetchType.LAZY)
        var products: MutableList<ProductStore> = mutableListOf()
}