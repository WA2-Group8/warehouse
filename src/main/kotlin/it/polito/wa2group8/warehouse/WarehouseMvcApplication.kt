package it.polito.wa2group8.warehouse

import it.polito.wa2group8.warehouse.repositories.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

//N.B: Not deleted because these imports are used if rows from 27 to 50 are uncommented
import it.polito.wa2group8.warehouse.repositories.CommentRepository
import it.polito.wa2group8.warehouse.repositories.WarehouseRepository
import it.polito.wa2group8.warehouse.repositories.ProductWarehouseRepository
import kotlin.random.Random
import it.polito.wa2group8.warehouse.domain.Product
import it.polito.wa2group8.warehouse.domain.ProductWarehouse
import it.polito.wa2group8.warehouse.domain.Warehouse
import java.math.BigDecimal

@SpringBootApplication
class WarehouseMvcApplication
{
    @Bean
    fun test(productRepository: ProductRepository, warehouseRepository: WarehouseRepository, productWarehouseRepository: ProductWarehouseRepository, commentRepository: CommentRepository): CommandLineRunner {
        return CommandLineRunner {
            /* UNCOMMENT TO ADD SOME DATA IN THE DB */

            /*
            val warehouses: MutableList<Warehouse> = mutableListOf()
            for (w in 1..10)
                warehouses.add(warehouseRepository.save(Warehouse(null, "w$w", "location$w")))

            val categories = arrayOf<String>("Food","Electronic","Home","Sport")
            for (i in 1..100)
            {
                val category = categories[Random.nextInt(0,categories.size-1)]
                val p = productRepository.save(Product(
                    null,"p$i", "description_p$i",
                    category, BigDecimal(Random.nextInt(1,100))
                ))

                warehouses.forEach {
                    val choose = Random.nextInt(0,2)
                    val quantity = Random.nextInt(0,10)
                    if (choose == 1)
                    {
                        productWarehouseRepository.save(ProductWarehouse(null, p, it, quantity))
                    }
                }
            }
             */
        }
    }
}

fun main(args: Array<String>)
{
    runApplication<WarehouseMvcApplication>(*args)
}
