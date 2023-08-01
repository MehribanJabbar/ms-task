package az.ingress.ms.controller

import az.ingress.ms.model.request.SaveCustomerRequest
import az.ingress.ms.model.response.CustomerResponse
import az.ingress.ms.service.CustomerService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CustomerControllerTest extends Specification {
    private CustomerService customerService
    private MockMvc mockMvc

    def setup() {
        customerService = Mock()
        def customerController = new CustomerController(customerService)
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build()
    }

    def "TestGetCustomerById"() {
        given:
        def id = 1L
        def url = "/v1/customers/$id"

        def responseView = new CustomerResponse(1L, "tonyStark", 30, "Baku", LocalDate.of(1993, 1, 1))

        def expectedResponse = '''
            {
                "id" : 1,
                "username" : "tonyStark",
                "age" : 30,
                "birthPlace" : "Baku",
                "birthDate" : [1993,1,1]
            }
    '''

        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * customerService.getCustomerById(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }


    def "TestSaveCustomer"() {
        given:
        def url = "/v1/customers"

        def dto = new SaveCustomerRequest("tonyStark", 30,
                "Baku", LocalDate.of(1993, 1, 1))

        def requestBody = '''
                {
                    "username" : "tonyStark",
                    "age" : 30,
                    "birthPlace" : "Baku",
                    "birthDate" : [1993,1,1]
                }
        '''

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn()

        then:
        1 * customerService.createCustomer(dto)

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }

}
