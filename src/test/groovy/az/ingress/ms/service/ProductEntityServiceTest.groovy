package az.ingress.ms.service

import az.ingress.ms.dao.entity.ProductEntity
import az.ingress.ms.dao.repository.ProductRepository
import az.ingress.ms.exception.NotFoundException
import az.ingress.ms.model.request.SaveProductRequest
import az.ingress.ms.model.request.UpdateProductRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ProductEntityServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private ProductService productService
    private ProductRepository productRepository

    def setup() {
        productRepository = Mock()
        productService = new ProductService(productRepository)
    }

    def "TestGetAllProducts"() {
        given:
        def product = random.nextObject(ProductEntity)

        when:
        def actual = productService.getAllProducts()

        then:
        1 * productRepository.findAll() >> List.of(product)
        actual[0].id == product.id
        actual[0].description == product.description
        actual[0].name == product.name
        actual[0].price == product.price
        actual[0].stock == product.stock
    }

    def "TestGetProductById success"() {
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(ProductEntity)

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

    def "TestGetProductById entity not found"() {
        given:
        def id = random.nextObject(Long)

        when:
        productService.getProductById(id)

        then:
        1 * productRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "PRODUCT_NOT_FOUND"
    }

    def "TestCreateProduct success"() {
        given:
        def request = random.nextObject(SaveProductRequest)
        def product = ProductEntity.builder()
                .name(request.name)
                .description(request.description)
                .price(request.price)
                .stock(request.stock)
                .build()

        when:
        productService.createProduct(request)

        then:
        1 * productRepository.save(product)
        product.name == request.name
        product.description == request.description
        product.stock == request.stock
        product.price == request.price
    }

    def "TestUpdateProduct success"() {
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateProductRequest)
        def product = random.nextObject(ProductEntity)

        when:
        productService.updateProduct(id, request)

        then:
        1 * productRepository.findById(id) >> Optional.of(product)
        1 * productRepository.save(product)
        request.name == product.name
        request.description == product.description
        request.price == product.price
        request.stock == product.stock
    }

    def "TestUpdateProduct entity not found"() {
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

    def "TestDeleteProduct success"() {
        given:
        def id = random.nextObject(Long)
        def product = random.nextObject(ProductEntity)

        when:
        productService.deleteProduct(id)

        then:
        1 * productRepository.findById(id) >> Optional.of(product)
        1 * productRepository.save(product)
    }

    def "TestDeleteProduct entity not found"() {
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
