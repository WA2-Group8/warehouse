package it.polito.wa2group8.warehousemvc.dto

import it.polito.wa2group8.warehousemvc.domain.Product
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ProductDTO (
    var id: Long,

    @get:NotEmpty
    val name: String,

    @get:NotEmpty
    val category: String,

    @get:DecimalMin(value="0.0", inclusive=true)
    val price: BigDecimal,

    val quantity: Int
)

fun Product.toProductDTO() = ProductDTO(id ?: -1, name, category, price, quantity)