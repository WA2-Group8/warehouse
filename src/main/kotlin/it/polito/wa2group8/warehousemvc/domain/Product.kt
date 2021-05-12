package it.polito.wa2group8.warehousemvc.domain

import javax.persistence.Id
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Min

@Entity
class Product(
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    var id: Long?,

    var name: String,

    var category: String,

    @get:DecimalMin(value="0.0", message="The value must be a positive or zero value", inclusive=true)
    var price: BigDecimal,

    @get:Min(value=0, message = "Quantity cannot be negative")
    var quantity: Int
)