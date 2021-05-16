package it.polito.wa2group8.warehousemvc.dto

import it.polito.wa2group8.warehousemvc.domain.Product

import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class ProductDTO (
    var id: Long?,

    @get:NotEmpty(message="Empty name")
    val name: String,

    @get:NotEmpty(message="Empty category")
    val category: String,

    @get:DecimalMin(value="0.0", message="Negative price", inclusive = true)
    val price: BigDecimal,

    val quantity: Int
)

fun Product.toProductDTO() = ProductDTO(id, name, category, price, quantity)
