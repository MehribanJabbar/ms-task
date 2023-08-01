package az.ingress.ms.controller

import az.ingress.ms.model.request.SaveAddressRequest
import az.ingress.ms.model.request.UpdateAddressRequest
import az.ingress.ms.model.response.AddressResponse
import az.ingress.ms.service.AddressService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

class AddressControllerTest extends Specification {

    private AddressService addressService
    private MockMvc mockMvc

    def setup() {
        addressService = Mock()
        def addressController = new AddressController(addressService)
        mockMvc = MockMvcBuilders.standaloneSetup(addressController)
                .build()
    }

    def "TestGetAllAddress"() {
        given:
        def url = "/v1/address"

        def dto1 = new AddressResponse(1L, "Main Street", "New York", "NY", "10001")
        def dto2 = new AddressResponse(2L, "Broadway street", "Los Angeles", "CA", "90002")

        def responseViewList = [dto1, dto2]

        def expectedResponse = '''
        [
            {
                "id" : 1,
                "street" : "Main Street",
                "city" : "New York",
                "state" : "NY",
                "postalCode" : "10001"
            },
            {
                "id" : 2,
                "street" : "Broadway street",
                "city" : "Los Angeles",
                "state" : "CA",
                "postalCode" : "90002"
            }
            ]
        '''

        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * addressService.getAllAddress() >> responseViewList

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
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

    def "TestUpdateAddress"() {
        given:
        def id = 1L
        def url = "/v1/address/$id"

        def dto = new UpdateAddressRequest("Main Street", "New York", "NY", "10001")

        def requestBody = '''
                {
                    "street" : "Main Street",
                    "city" : "New York",
                    "state" : "NY",
                    "postalCode" : "10001"
                }
        '''

        when:
        def result = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn()

        then:
        1 * addressService.updateAddress(id, dto)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }

    def "TestDeleteAddress"() {
        given:
        def id = 1L
        def url = "/v1/address/$id"

        when:
        def result = mockMvc.perform(delete(url)).andReturn()

        then:
        1 * addressService.deleteAddress(id)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }

}
