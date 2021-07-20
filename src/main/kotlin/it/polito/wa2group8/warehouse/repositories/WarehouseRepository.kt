package it.polito.wa2group8.warehouse.repositories

//import it.polito.wa2group8.warehouse.domain.ProductStore
import it.polito.wa2group8.warehouse.domain.Warehouse
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WarehouseRepository : CrudRepository<Warehouse, Long>
{
    //fun findAllByProductsContaining(products: MutableSet<ProductStore>): Iterable<Warehouse>
}
