package it.polito.wa2group8.warehouse.mappers

import it.polito.wa2group8.warehouse.domain.Product
import it.polito.wa2group8.warehouse.dto.ProductDTO
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateProductFromDto(dto: ProductDTO, @MappingTarget entity: Product)
}