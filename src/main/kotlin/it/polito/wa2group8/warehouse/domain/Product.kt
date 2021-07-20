package it.polito.wa2group8.warehouse.domain

import it.polito.wa2group8.warehouse.dto.ProductDTO
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Entity
class Product(
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "product_id")
    var productId: Long?,

    @get:NotEmpty
    @get:NotBlank
    var name: String,

    @get:NotEmpty
    @get:NotBlank
    var description: String,

    @get:NotEmpty
    var pictureURL: String,

    @get:NotEmpty
    @get:NotBlank
    var category: String,

    @get:DecimalMin(value="0.0", message="The value must be a positive or zero value", inclusive=true)
    var price: BigDecimal,

    @get:DecimalMin(value="0.0", message="The value must be a positive or zero value", inclusive=true)
    var averageRating: BigDecimal,

    @get:DateTimeFormat
    var creationDate: Date,

    ){
    @OneToMany(mappedBy = "product", targetEntity = ProductWarehouse::class, fetch = FetchType.LAZY)
    var warehouses: MutableSet<ProductWarehouse> = mutableSetOf()

    @OneToMany(mappedBy = "product", targetEntity = Comment::class, fetch = FetchType.LAZY)
    var comments: MutableSet<Comment> = mutableSetOf()

    /*
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
            name = "product_warehouse",
            joinColumns = [JoinColumn(name = "product_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "warehouse_id", referencedColumnName = "id")]
    )
    var warehouse: MutableSet<Warehouse> = mutableSetOf()

     */

    //@OneToMany(mappedBy = "product")
    //val warehouses: MutableSet<WarehouseProduct> = mutableSetOf()

    //@ManyToMany(targetEntity = Warehouse::class, fetch = FetchType.LAZY)
    //@ManyToMany
    //var warehouses: MutableSet<Warehouse> = mutableSetOf()

    fun toProductDTO() = ProductDTO(productId, name, description, pictureURL, category, price, averageRating, creationDate)
}
