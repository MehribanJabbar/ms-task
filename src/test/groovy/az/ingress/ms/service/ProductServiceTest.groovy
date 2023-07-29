package az.ingress.ms.service

import az.ingress.ms.dao.entity.Product
import az.ingress.ms.dao.repository.ProductRepository
import az.ingress.ms.exception.NotFoundException
import az.ingress.ms.mapper.ProductMapper
import az.ingress.ms.model.request.SaveProductRequest
import az.ingress.ms.model.request.UpdateProductRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ProductServiceTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private ProductService productService
    private ProductRepository productRepository

    def setup(){
        productRepository = Mock()
        productService = new ProductService(productRepository)
    }

    def "TestProductById success"(){
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(Product)

        when:
        def actual = productService.getProductById(id)

        then:
        1 * productRepository.findById(id) >> Optional.of(entity)
        actual.id == entity.id
        actual.description == entity.description
        actual.name == entity.name
        actual.price == entity.price
        actual.stock == entity.stock
    }

    def "TestGetProductById entity not found"(){
        given:
        def id = random.nextObject(Long)

        when:
        productService.getProductById(id)

        then:
        1 * productRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "PRODUCT_NOT_FOUND"
    }

//    def "TestCreateProduct success"(){
//        given:
//        def request = new SaveProductRequest()
//        def product = ProductMapper.buildToEntity(request)
//
//        when:
//        productService.createProduct(request)
//
//        then:
//        1 * productRepository.save(product)
//    }

    def "TestCreateProduct success"(){
        given:
        def request = new SaveProductRequest()
        def product = new Product()

        when:
        productService.createProduct(request)

        then:
        1 * productRepository.save(product)
        product.stock == request.stock
        product.price == request.price
        product.name == request.name
        product.description == request.description
    }

    def "TestUpdateProduct success"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateProductRequest)
        def product = random.nextObject(Product)

        when:
        productService.updateProduct(id, request)

        then:
        1 * productRepository.findById(id) >> Optional.of(product)
        1 * productRepository.save(product)
    }

    def "TestUpdateProduct entity not found"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateProductRequest)

        when:
        productService.updateProduct(id, request)

        then:
        1 * productRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "PRODUCT_NOT_FOUND"
    }

    def "TestDeleteProduct success"(){
        given:
        def id = random.nextObject(Long)
        def product = random.nextObject(Product)

        when:
        productService.deleteProduct(id)

        then:
        1 * productRepository.findById(id) >> Optional.of(product)
        1 * productRepository.save(product)
    }

    def "TestDeleteProduct entity not found"(){
        given:
        def id = random.nextObject(Long)

        when:
        productService.deleteProduct(id)

        then:
        1 * productRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "PRODUCT_NOT_FOUND"
    }
}
