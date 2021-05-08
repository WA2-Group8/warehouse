package it.polito.wa2group8.warehousemvc.domain

import org.hibernate.annotations.Table
import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.math.BigInteger

//@Table("products")
class Product(
    @Id
    var id: Long?,
    var name: String,
    var category: String,
    var price: BigDecimal,
    var quantity: Int
)