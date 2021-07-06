package it.polito.wa2group8.warehousemvc.domain

import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

@Entity
class Product(
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        var id: Long?,

        @get:NotEmpty
        var name: String,

        @get:NotEmpty
        var description: String,

        @get:NotEmpty
        var pictureURL: String,

        @get:NotEmpty
        var category: String,

        @get:DecimalMin(value="0.0", message="The value must be a positive or zero value", inclusive=true)
        var price: BigDecimal,

        @get:DecimalMin(value="0.0", message="The value must be a positive or zero value", inclusive=true)
        var averageRating: BigDecimal,

        @get:DateTimeFormat
        var creationDate: Date

){
        @OneToMany(mappedBy = "product", targetEntity = Comment::class, fetch = FetchType.LAZY)
        var comments: MutableList<Comment> = mutableListOf()

        @OneToMany(mappedBy = "product", targetEntity = ProductStore::class, fetch = FetchType.LAZY)
        var warehouses: MutableList<ProductStore> = mutableListOf()
}