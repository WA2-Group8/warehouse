//package it.polito.wa2group8.warehousemvc
//
//import it.polito.wa2group8.warehousemvc.domain.Product
//import it.polito.wa2group8.warehousemvc.repositories.ProductRepository
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertThrows
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.test.context.TestPropertySource
//import java.math.BigDecimal
//import javax.persistence.EntityManager
//import javax.validation.ConstraintViolationException
//
//@DataJpaTest
//@TestPropertySource(properties = [
//    "spring.datasource.url=jdbc:h2:mem:test",
//    "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect",
//    "spring.jpa.hibernate.ddl-auto = create-drop",
//    "spring.jpa.properties.hibernate.show_sql = true",
//    "spring.jpa.properties.hibernate.format_sql = true"
//])
//class ProductTest {
//    @Autowired
//    lateinit var productRepository: ProductRepository
//
//    @Autowired
//    lateinit var entityManager: EntityManager
//
//    @Test
//    fun negativePriceRejected() {
//        val p = Product(null, "p1", "c1", BigDecimal("-1"), 1)
//        assertThrows<ConstraintViolationException> {
//            productRepository.save(p)
//            entityManager.flush()
//        }
//    }
//
//    @Test
//    fun negativeQuantityRejected() {
//        val p = Product(null, "p1", "c1", BigDecimal("1"), -1)
//        assertThrows<ConstraintViolationException> {
//            productRepository.save(p)
//            entityManager.flush()
//        }
//    }
//
//    @Test
//    fun updateQuantity() {
//        productRepository.save(Product(null, "p1", "c1", BigDecimal("1"), 1))
//        val i = productRepository.updateQuantity(2, 1)
//        assertThat(i).isEqualTo(1)
//    }
//
//    @Test
//    fun findByCategory() {
//        productRepository.save(Product(null, "p1", "c1", BigDecimal("1"), 1))
//        productRepository.save(Product(null, "p2", "c1", BigDecimal("1"), 1))
//        productRepository.save(Product(null, "p3", "c2", BigDecimal("1"), 1))
//        assertThat(productRepository.findByCategory("c1").count()).isEqualTo(2)
//    }
//}
