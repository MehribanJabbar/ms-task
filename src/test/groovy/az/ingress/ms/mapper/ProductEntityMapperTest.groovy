package az.ingress.ms.mapper

import az.ingress.ms.dao.entity.ProductEntity
import az.ingress.ms.model.request.SaveProductRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ProductEntityMapperTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private ProductMapper productMapper

    def "TestBuildToResponse"(){
        given:
        def product = random.nextObject(ProductEntity)

        when:
        def actual = ProductMapper.buildToResponse(product)

        then:
        actual.id == product.id
        actual.description == product.description
        actual.name == product.name
        actual.price == product.price
        actual.stock == product.stock
    }

    def "TestBuildToEntity"(){
        given:
        def product = random.nextObject(SaveProductRequest)

        when:
        def actual = ProductMapper.buildToEntity(product)

        then:
        actual.stock == product.stock
        actual.price == product.price
        actual.name == product.name
        actual.description == product.description
    }
}
