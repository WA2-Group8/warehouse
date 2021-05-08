package it.polito.wa2group8.warehousemvc.domain

import org.hibernate.annotations.Table
import javax.persistence.Id
import java.math.BigDecimal
import java.math.BigInteger
import javax.persistence.Entity

//@Table("products")
@Entity
class Product(
    @Id
    var id: Long?,
    var name: String,
    var category: String,
    var price: BigDecimal,
    var quantity: Int
)