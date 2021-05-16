package it.polito.wa2group8.warehousemvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WarehouseMvcApplication
{
    /* UNCOMMENT TO ADD 10000 RANDOM ROWS TO THE TABLE "PRODUCT" */
    /*
    @Bean
    fun test(productRepository: ProductRepository): CommandLineRunner {
        return CommandLineRunner{
            val categories = arrayOf<String>("Food","Electronic","Home","Sport")
            for (i in 1..10000){
                val category = categories[Random.nextInt(0,categories.size-1)]
                productRepository.save(Product(null,"${category}_$i",category, BigDecimal(Random.nextInt(1,1000)), Random.nextInt(1,100)))
            }
        }
    }
    */
}

fun main(args: Array<String>)
{
    runApplication<WarehouseMvcApplication>(*args)
}
