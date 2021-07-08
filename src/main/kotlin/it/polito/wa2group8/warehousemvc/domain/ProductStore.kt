package it.polito.wa2group8.warehousemvc.domain

import javax.persistence.*

@Entity
class ProductStore (
        @EmbeddedId
        var id: ProductStoreKey?,

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("productId")
        @JoinColumn(name = "product", referencedColumnName = "id")
        var product: Product,

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("warehouseId")
        @JoinColumn(name = "warehouse", referencedColumnName = "id")
        var warehouse: Warehouse,

        var quantity: Int
)