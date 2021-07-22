package it.polito.wa2group8.warehouse.controllers

import it.polito.wa2group8.warehouse.dto.CommentDTO
import it.polito.wa2group8.warehouse.dto.ProductDTO
import it.polito.wa2group8.warehouse.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ProductController(val productService: ProductService)
{
    @GetMapping(value = ["/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProducts(@RequestParam("category", required = false) category: String?): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.getProducts(category))
    }

    @GetMapping(value = ["/products/{productID}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProductByID(@PathVariable productID: Long): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.getProductById(productID))
    }

    @PostMapping(value = ["/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createProduct(@RequestBody @Valid product: ProductDTO): ResponseEntity<Any>
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createOrUpdateProduct(null, product))
    }

    @PutMapping(value = ["/products/{productID}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun updateOrCreateProduct(@PathVariable productID: Long, @RequestBody @Valid product: ProductDTO): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.createOrUpdateProduct(productID, product))
    }

    @PatchMapping(value = ["/products/{productID}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun updateProduct(@PathVariable productID: Long, @RequestBody @Valid product: ProductDTO): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.updateProduct(productID, product))
    }

    @DeleteMapping(value = ["/products/{productID}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun deleteProduct(@PathVariable productID: Long): ResponseEntity<Any>
    {
        productService.deleteProduct(productID)
        return ResponseEntity.noContent().build()
    }

    @GetMapping(value = ["/products/{productID}/picture"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProductPicture(@PathVariable productID: Long): ResponseEntity<Any>
    {
        TODO("Not yet implemented")
    }

    @PostMapping(value = ["/products/{productID}/picture"])
    @ResponseBody
    fun addProductPicture(@PathVariable productID: Long,
//        @RequestBody @Valid product: ProductDTO,
    ): ResponseEntity<Any>
    {
        TODO("Not yet implemented")
    }

    @GetMapping(value = ["/products/{productID}/warehouses"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProductWarehouses(@PathVariable productID: Long): ResponseEntity<Any>
    {
        return ResponseEntity.ok().body(productService.getProductWarehouses(productID))
    }

    @PostMapping(value = ["products/{productID}/comments"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addCommentToProduct(
        @PathVariable("productID") productID: Long,
        @RequestBody @Valid commentDTO: CommentDTO,
        bindingResult: BindingResult
    ): ResponseEntity<Any>
    {
        println("qui")
        if (bindingResult.hasErrors()) return ResponseEntity.badRequest().body(bindingResult.fieldErrors)
        productService.addComment(productID, commentDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body("")
    }
}
