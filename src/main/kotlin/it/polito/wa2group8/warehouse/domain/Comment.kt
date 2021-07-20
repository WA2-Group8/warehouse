package it.polito.wa2group8.warehouse.domain

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Entity
class Comment (
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    var id: Long?,

    @get:NotEmpty
    var title: String,

    @get:NotEmpty
    var body: String,

    @get:Min(value=0, message = "Stars cannot be negative")
    @get:Max(value=5, message = "Stars cannot be more than five")
    var stars: Int,

    @get:DateTimeFormat
    var creationDate: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "product_id")
    var product: Product
)