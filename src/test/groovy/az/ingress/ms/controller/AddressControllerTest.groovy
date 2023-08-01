package az.ingress.ms.controller

import az.ingress.ms.model.request.SaveAddressRequest
import az.ingress.ms.model.response.AddressResponse
import az.ingress.ms.service.AddressService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class AddressControllerTest extends Specification {

    private AddressService addressService
    private MockMvc mockMvc

    def setup() {
        addressService = Mock()
        def addressController = new AddressController(addressService)
        mockMvc = MockMvcBuilders.standaloneSetup(addressController)
                .build()
    }

    def "TestGetAddressById"() {
        given:
        def id = 1L
        def url = "/v1/address/$id"

        def responseView = new AddressResponse(1L, "Main Street", "New York", "NY", "10001")

        def expectedResponse = '''
                {
                    "id" : 1,
                    "street" : "Main Street",
                    "city" : "New York",
                    "state" : "NY",
                    "postalCode" : "10001"
                }
        '''

        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * addressService.getAddressById(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }


    def "TestSaveAddress"() {
        given:
        def url = "/v1/address"

        def dto = new SaveAddressRequest("Main Street", "New York", "NY", "10001")

        def requestBody = '''
                {
                    "street" : "Main Street",
                    "city" : "New York",
                    "state" : "NY",
                    "postalCode" : "10001"
                }
        '''

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn()

        then:
        1 * addressService.createAddress(dto)

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }

}
