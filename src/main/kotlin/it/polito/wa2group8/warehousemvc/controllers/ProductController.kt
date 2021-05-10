package it.polito.wa2group8.warehousemvc.controllers

import it.polito.wa2group8.warehousemvc.dto.ProductDTO
import it.polito.wa2group8.warehousemvc.dto.QuantityDTO
import it.polito.wa2group8.warehousemvc.services.ProductService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ProductController(
    val productService: ProductService
) {
    @PostMapping(value=["/warehouse/products"])
    @ResponseBody
    fun createProduct(
        @RequestBody @Valid product: ProductDTO,
        //bindingResult: BindingResult
    ): ResponseEntity<Any>
    {
        //if(bindingResult.hasErrors()) return ResponseEntity.badRequest().body(bindingResult.getFieldError("customerId")?.defaultMessage)
        return ResponseEntity.status(201).body(productService.createProduct(product))
    }

    @PatchMapping(value=["/warehouse/products/{productID}"])
    @ResponseBody
    fun updateProduct(
        @PathVariable productID: Long,
        @RequestBody quantityDTO: QuantityDTO
    ): ResponseEntity<Any>
    {
        return ResponseEntity.status(200).body(productService.updateProduct(quantityDTO.quantity, productID))
    }

    @GetMapping(value=["/warehouse/products/{productID}"], produces=[MediaType.APPLICATION_JSON_VALUE])
    fun getProductByID(@PathVariable productID: Long): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.retrieveProduct(productID))
    }

    @GetMapping(value=["/warehouse/products"], produces=[MediaType.APPLICATION_JSON_VALUE])
    fun getAllProducts(): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.retrieveAllProducts())
    }

    @GetMapping(value=["/warehouse/productsByCategory"], produces=[MediaType.APPLICATION_JSON_VALUE])
    fun getProductsByCategory(
        @RequestParam("category", required=true) category: String
    ): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.retrieveProductsByCategory(category))
    }
}