package it.polito.wa2group8.warehouse

import it.polito.wa2group8.warehouse.repositories.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

//N.B: Not deleted because these imports are used if rows from 20 to 26 are uncommented
import it.polito.wa2group8.warehouse.repositories.CommentRepository
//import it.polito.wa2group8.warehouse.repositories.ProductStoreRepository
import it.polito.wa2group8.warehouse.repositories.WarehouseRepository

@SpringBootApplication
class WarehouseMvcApplication
{
    @Bean
    fun test(productRepository: ProductRepository, warehouseRepository: WarehouseRepository, commentRepository: CommentRepository): CommandLineRunner {//, productStoreRepository: ProductStoreRepository): CommandLineRunner {
        return CommandLineRunner{
            /* UNCOMMENT TO ADD 10000 RANDOM ROWS TO THE TABLE "PRODUCT" */
            /*
            val categories = arrayOf<String>("Food","Electronic","Home","Sport")
            for (i in 1..10000){
                val category = categories[Random.nextInt(0,categories.size-1)]
                productRepository.save(Product(null,"${category}_$i",category, BigDecimal(Random.nextInt(1,1000)), Random.nextInt(1,100)))
            }
            */
//            val warehouse = warehouseRepository.save(Warehouse(null))
//            val product = productRepository.save(Product(null, "p1", "test", "url", "c1", BigDecimal("0.5"), BigDecimal("0.5"), Date()))
//            val comment = commentRepository.save(Comment(null, "t1", "c1", 4, Date(), product))
//            val key = ProductStoreKey(product.id, warehouse.id)
//            productStoreRepository.save(ProductStore(key, product, warehouse, 10))
        }
    }
}

fun main(args: Array<String>)
{
    runApplication<WarehouseMvcApplication>(*args)
}