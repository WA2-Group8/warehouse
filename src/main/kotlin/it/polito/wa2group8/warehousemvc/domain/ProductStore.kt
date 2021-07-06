package it.polito.wa2group8.warehousemvc.domain

import javax.persistence.*

@Entity
class ProductStore {
    @EmbeddedId
    var id: ProductStoreKey? = null

    @ManyToOne
    //@MapsId("studentId")
    @JoinColumn(name = "product", referencedColumnName = "productId")
    var product: Product? = null

    @ManyToOne
    //@MapsId("courseId")
    @JoinColumn(name = "warehouse", referencedColumnName = "warehouseId")
    var warehouse: Warehouse? = null

    var quantity = 0
}