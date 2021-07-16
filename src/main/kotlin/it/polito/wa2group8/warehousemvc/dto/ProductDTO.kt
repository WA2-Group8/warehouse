package it.polito.wa2group8.warehousemvc.dto

import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class ProductDTO (
    var id: Long?,

    @get:NotEmpty(message="Empty name")
    val name: String?,

    @get:NotEmpty
    var description: String?,

    @get:NotEmpty
    var pictureURL: String?,

    @get:NotEmpty(message="Empty category")
    val category: String?,

    @get:DecimalMin(value="0.0", message="Negative price", inclusive = true)
    val price: BigDecimal?,

    @get:DecimalMin(value="0.0", message="The value must be a positive or zero value", inclusive=true)
    var averageRating: BigDecimal?,

    @get:DateTimeFormat
    var creationDate: Date?
)


