package it.polito.wa2group8.warehousemvc.domain


import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
    @Id
    val id: Long?
)
{
}