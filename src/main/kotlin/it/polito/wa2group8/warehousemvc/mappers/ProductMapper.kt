package it.polito.wa2group8.warehousemvc.mappers

import it.polito.wa2group8.warehousemvc.domain.Product
import it.polito.wa2group8.warehousemvc.dto.ProductDTO
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateProductFromDto(dto: ProductDTO, @MappingTarget entity: Product)
}