package it.polito.wa2group8.warehousemvc.domain

import java.io.Serializable
import javax.persistence.Embeddable


@Embeddable
class ProductStoreKey : Serializable {

    var productId: Long? = null

    var warehouseId: Long? = null

    // hashcode and equals implementation
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductStoreKey

        if (productId != other.productId) return false
        if (warehouseId != other.warehouseId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productId?.hashCode() ?: 0
        result = 31 * result + (warehouseId?.hashCode() ?: 0)
        return result
    }
}