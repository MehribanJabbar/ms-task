package az.ingress.ms.controller

import az.ingress.ms.model.request.SaveProductRequest
import az.ingress.ms.model.response.ProductResponse
import az.ingress.ms.service.ProductService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class ProductControllerTest extends Specification {
    private ProductService productService
    private MockMvc mockMvc

    def setup() {
        productService = Mock()
        def productController = new ProductController(productService)
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build()
    }

    def "TestGetProductById"() {
        given:
        def id = 1L
        def url = "/v1/products/$id"

        def responseView = new ProductResponse(1L, "Macbook", "Pro", 6000.00, 12)

        def expectedResponse = '''
                {
                    "id" : 1,
                    "name" : "Macbook",
                    "description" : "Pro",
                    "price" : 6000.00,
                    "stock" : 12
                }
        '''

        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * productService.getProductById(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }


    def "TestSaveProduct"() {
        given:
        def url = "/v1/products"

        def dto = new SaveProductRequest("Macbook", "Pro", 6000.00, 12)

        def requestBody = '''
                {
                    "name" : "Macbook",
                    "description" : "Pro",
                    "price" : 6000.00,
                    "stock" : 12
                }
        '''

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn()

        then:
        1 * productService.createProduct(dto)

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }

}
